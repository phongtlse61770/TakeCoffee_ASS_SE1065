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

        [JsonProperty("category")]
        public CategoryJsonModel Category { get; set; }

        [JsonProperty("quantity", NullValueHandling = NullValueHandling.Ignore)]
        public int? Quantity { get; set; }

        [JsonProperty("isAvailable", NullValueHandling = NullValueHandling.Ignore)]
        public bool? IsAvailable { get; set; }

        public static ProductJsonModel FromEntity(Product product)
        {
            return new ProductJsonModel
            {
                ID = product.ID,
                Name = product.name,
                Price = product.unitPrice,
                Category = (CategoryJsonModel) product.Category,
                Quantity = null,
                IsAvailable = true
            };
        }

        public static ProductJsonModel FromEntity(OrderProduct orderProduct)
        {
            Product product = orderProduct.Product;
            return new ProductJsonModel
            {
                ID = product.ID,
                Name = product.name,
                Price = orderProduct.unitPrice,
                Category = (CategoryJsonModel) product.Category,
                Quantity = orderProduct.quantity,
                IsAvailable = null
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