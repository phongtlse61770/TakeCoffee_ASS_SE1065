using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entity.Helper
{
    public class OrderHelper : BaseEntityHelper
    {
        public ICollection<Order> GetAllOrder()
        {
            return db.Orders.ToList<Order>();
        }

        public Decimal CalculateOrderPrice(Order order)
        {
            decimal totalPrice = 0;
            foreach (OrderProduct orderProduct in order.OrderProducts)
            {
                totalPrice += orderProduct.unitPrice.Value * orderProduct.quantity.Value;
            }
            return totalPrice;
        }

        public Decimal CalculateOrderPrice(Order order,decimal shipfee)
        {
            return CalculateOrderPrice(order) + shipfee;
        }

        /// <summary>
        /// Add new order
        /// </summary>
        /// <param name="productList">productID,quantity </param>
        /// <param name="userID"></param>
        /// <param name="employeeID"></param>
        /// <returns></returns>
        public static int CreateOrder(IDictionary<int, int> productList, int userID, int employeeID)
        {
            return 0;
        }
    }
}