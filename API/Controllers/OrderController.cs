using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using Entity;
using Entity.Helper;
using Entity.JsonModel;
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
                    orderJsonModels.Add((OrderJsonModel)order);
                }
                
                return Ok(orderJsonModels);
            }
           
        }
    }
}