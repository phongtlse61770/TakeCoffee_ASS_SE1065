using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using Entity;
using Entity.Helper;
using Entity.JsonModel;
using Microsoft.Ajax.Utilities;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace API.Controllers
{
    [RoutePrefix("order")]
    public class OrderController : ApiController
    {
        [HttpPost]
        [Route("all")]
        public IHttpActionResult Index()
        {
            using (var orderHelper = new OrderHelper())
            {
                ICollection<Order> orders = orderHelper.GetAllOrder();
                ICollection<OrderJsonModel> orderJsonModels = new List<OrderJsonModel>();

                foreach (Order order in orders)
                {
                    orderJsonModels.Add((OrderJsonModel) order);
                }
                return Ok(orderJsonModels);
            }
        }

        [HttpPost]
        [Route("create")]
        public IHttpActionResult Order([FromBody] JObject jObject)
        {
            IDictionary<int, int> productList;
            Decimal shipfee;
            try
            {
                productList = new Dictionary<int, int>();
                shipfee = jObject["shipfee"].Value<Decimal>();
                JArray jArray = jObject["order_detail"].Value<JArray>();
                foreach (JObject orderDetail in jArray)
                {
                    int productId = orderDetail["id"].Value<int>();
                    int quantity = orderDetail["quantity"].Value<int>();
                    productList.Add(productId, quantity);
                }
            }
            catch (Exception)
            {
                return BadRequest("Invalid request");
            }
            //----------------------------------------------

            string username = Request.Headers.GetValues("username").First();
            string password = Request.Headers.GetValues("password").First();

            using (var orderHelper = new OrderHelper())
            {

                using (var userHelper = new UserHelper())
                {
                    Decimal totalCost = orderHelper.CalculateOrderPrice(productList, shipfee);
                    int userId = userHelper.GetUser(username, password).ID;
                    Order order = orderHelper.CreateOrder(productList, userId);
                    bool isSuccess = false;

                    if (order != null)
                    {
                        if(orderHelper.AddOrder(order))
                            isSuccess = userHelper.RemoveBalance(userId, totalCost);
                    }

                    JObject response = new JObject
                    {
                        ["result"] = isSuccess
                    };
                    return Ok(response);
                }
            }
        }
    }
}