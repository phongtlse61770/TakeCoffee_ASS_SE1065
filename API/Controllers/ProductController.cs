using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web.Http;
using Entity;
using Entity.Helper;
using Entity.JsonModel;
using Newtonsoft.Json.Linq;


namespace API.Controllers
{
    [RoutePrefix("product")]
    public class ProductController : ApiController
    {
        [HttpPost]
        [Route("all")]
        public IHttpActionResult GetAllProduct()
        {
            using (ProductHelper productHelper = new ProductHelper())
            {
                ICollection<ProductJsonModel> productJsonModels = new List<ProductJsonModel>();
                foreach (Product product in productHelper.GetAllProduct())
                {
                    productJsonModels.Add((ProductJsonModel) product);
                }
                return Ok(productJsonModels);
            }
        }

        [HttpPost]
        [Route("menu")]
        public IHttpActionResult GetMenu()
        {
            using (ProductHelper productHelper = new ProductHelper())
            {
                ICollection<ProductJsonModel> productJsonModels = new List<ProductJsonModel>();
                foreach (Product product in productHelper.GetAllProduct())
                {
                    ProductJsonModel productJsonModel = (ProductJsonModel) product;
                    if (!product.isDisabled)
                    {
                        productJsonModels.Add(productJsonModel);
                    }
                }
                return Ok(productJsonModels);
            }
        }
    }
}