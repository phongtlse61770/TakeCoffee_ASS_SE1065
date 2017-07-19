using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace Entity.JsonModel
{
    [JsonObject(Title = "product")]
    public class ProductJsonModel
    {
        [JsonProperty("ID")]
        public int ID { get; set; }

        [JsonProperty("name")]
        public String Name { get; set; }

        [JsonProperty("price", NullValueHandling = NullValueHandling.Ignore)]
        public Decimal? Price { get; set; }

        [JsonProperty("category", NullValueHandling = NullValueHandling.Ignore)]
        public CategoryJsonModel Category { get; set; }

        [JsonProperty("quantity", NullValueHandling = NullValueHandling.Ignore)]
        public int? Quantity { get; set; }

        [JsonProperty("isAvailable", NullValueHandling = NullValueHandling.Ignore)]
        public bool? IsAvailable { get; set; }

        [JsonProperty("image", NullValueHandling = NullValueHandling.Ignore)]  
        private string Image { get; set; }

        internal static ProductJsonModel FromEntity(Product product,bool category = true)
        {
            ProductJsonModel productJsonModel = new ProductJsonModel
            {
                ID = product.ID,
                Name = product.name,
                Price = product.unitPrice,
//                Category = (CategoryJsonModel) product.Category,
                Quantity = null,
                IsAvailable = true,
                Image = product.image
            };
            if (category)
            {
                productJsonModel.Category = (CategoryJsonModel) product.Category;
            }
            return productJsonModel;
        }

        internal static ProductJsonModel FromEntity(OrderProduct orderProduct)
        {
            Product product = orderProduct.Product;
            return new ProductJsonModel
            {
                ID = product.ID,
                Name = product.name,
                Price = orderProduct.unitPrice,
                Category = (CategoryJsonModel) product.Category,
                Quantity = orderProduct.quantity,
                IsAvailable = null,
                Image = product.image
            };
        }

        public static explicit operator ProductJsonModel(Product product)
        {
            return FromEntity(product);
        }

        public static explicit operator ProductJsonModel(OrderProduct orderProduct)
        {
            return FromEntity(orderProduct);
        }
    }
}