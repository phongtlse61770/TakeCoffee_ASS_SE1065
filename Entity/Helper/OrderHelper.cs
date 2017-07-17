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

        public Decimal CalculateOrderPrice(IDictionary<int, int> productList, decimal shipfee)
        {
            Decimal totalPrice = 0;
            foreach (KeyValuePair<int, int> valuePair in productList)
            {
                int productId = valuePair.Key;
                int quantity = valuePair.Value;
                Product product = db.Products.Find(productId);

                totalPrice += quantity * product.unitPrice.Value;
            }
            totalPrice += shipfee;
            return totalPrice;
        }

        public bool AddOrder(Order order)
        {
            db.Orders.Add(order);
            return db.SaveChanges() > 0;
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
        public Order CreateOrder(IDictionary<int, int> productList, int customerId, int? employeeId = null)
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
                //--------------------------
                order.OrderProducts = new List<OrderProduct>();
                foreach (KeyValuePair<int, int> valuePair in productList)
                {
                    int productId = valuePair.Key;
                    int quantity = valuePair.Value;
                    Product product = db.Products.Find(productId);
                    OrderProduct orderProduct = new OrderProduct
                    {
                        producID = product.ID,
                        quantity = quantity,
                        unitPrice = product.unitPrice
                    };
                    order.OrderProducts.Add(orderProduct);
                }

                return order;
            }
            catch (Exception)
            {
                return null;
            }
        }
    }
}