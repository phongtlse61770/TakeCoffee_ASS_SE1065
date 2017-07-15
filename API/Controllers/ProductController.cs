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
            using (CategoryHelper categoryHelper = new CategoryHelper())
            {
                using (ProductHelper productHelper = new ProductHelper())
                {
                    ICollection<CategoryJsonModel> categoryJsonModels = new List<CategoryJsonModel>();
                    foreach (Category category in categoryHelper.GetAllCategory())
                    {
                        ICollection<ProductJsonModel> productJsonModels = new List<ProductJsonModel>();
                        foreach (Product product in productHelper.GetProductByCaterory(category.ID))
                        {
                            ProductJsonModel productJsonModel = (ProductJsonModel) product;
                            productJsonModels.Add(productJsonModel);
                        }
                        CategoryJsonModel categoryJsonModel = (CategoryJsonModel) category;
                        categoryJsonModel.ProductList = productJsonModels;
                        categoryJsonModels.Add(categoryJsonModel);

                    }
                    return Ok(categoryJsonModels);
                }
            }
        }
    }
}