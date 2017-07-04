using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace Entity.JsonModel
{
    [JsonObject("Order")]
    public class OrderJsonModel
    {
        /// <summary>
        /// ID int identity
        ///		constraint PK_Order
        ///			primary key,
        ///	createdTime datetime,
        ///	employeeID int
        ///		constraint FK_Order_Employee
        ///			references User,
        ///	customerID int
        ///		constraint FK_Order_Customer
        ///			references User
        /// </summary>|
        [JsonProperty("ID")]
        public int ID;
        [JsonProperty("create_name")]
        public DateTime? CreatedTime{ get; set; }
        [JsonProperty("employee")]
        public UserJsonModel Employee { get; set; }
        [JsonProperty("customer")]
        public UserJsonModel Customer { get; set; }
        [JsonProperty("products")]
        public ICollection<ProductJsonModel> ProductJsonModels { get; set; }


        public static OrderJsonModel FromEntity(Order order)
        {
            ICollection<ProductJsonModel> productJsonModels = new List<ProductJsonModel>();
            foreach (OrderProduct orderProduct in order.OrderProducts)
            {
                productJsonModels.Add((ProductJsonModel)orderProduct);
            }

            return new OrderJsonModel
            {
                ID = order.ID,
                CreatedTime = order.createdTime,
                Employee = order.User1,
                Customer = order.User,
                ProductJsonModels = productJsonModels
            };
        }

        public static explicit operator OrderJsonModel(Order order)
        {
            return FromEntity(order);
        }
    }
}
