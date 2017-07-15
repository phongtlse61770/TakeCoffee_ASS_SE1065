using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace Entity.JsonModel
{
    [JsonObject(Title = "category")]
    public class CategoryJsonModel
    {
        [JsonProperty("ID")]
        public int ID { get; set; }

        [JsonProperty("name")]
        public String Name { get; set; }

        [JsonProperty("products",NullValueHandling = NullValueHandling.Ignore)] 
        public ICollection<ProductJsonModel> ProductList { get; set; }

        internal static CategoryJsonModel FromEntity(Category category)
        {
            return new CategoryJsonModel
            {
                ID = category.ID,
                Name = category.name
            };
        }

        public static explicit operator CategoryJsonModel(Category category)
        {
            return FromEntity(category);
        }
    }
}