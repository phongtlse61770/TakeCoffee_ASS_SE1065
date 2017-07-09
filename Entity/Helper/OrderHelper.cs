using System;
using System.Collections;
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

        public Decimal CalculateOrderPrice(Order order, decimal shipfee)
        {
            return CalculateOrderPrice(order) + shipfee;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="productList">include the product entity and its quantity</param>
        /// <param name="customerId"></param>
        /// <param name="employeeId"></param>
        /// <returns></returns>
        public Order CreateOrder(IDictionary<Product,int> productList, int customerId, int employeeId)
        {
            try
            {
                Order order = new Order
                {
                    createdTime = DateTime.Now,
                    customerID = customerId,
                    employeeID = employeeId,
                    isConfirmed = false
                };
                
                db.Orders.Add(order);
                db.SaveChanges();
                return order;
            }
            catch (Exception)
            {
                return null;
            }
        }
    }
}