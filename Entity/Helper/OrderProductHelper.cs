using System;
using System.Collections.Generic;

namespace Entity.Helper
{
    public class OrderProductHelper : BaseEntityHelper
    {
        /// <summary>
        /// 
        /// </summary>
        /// <param name="products">include the product entity and its quantity</param>
        /// <param name="orderId"></param>
        /// <returns></returns>
        public ICollection<OrderProduct> InsertOrderProduct(IDictionary<Product,int> products,int orderId)
        {
            try
            {
                ICollection<OrderProduct> orderProducts = new List<OrderProduct>();
                foreach (var entry in products)
                {
                    int quantity = entry.Value;
                    Product product = entry.Key;

                    OrderProduct orderProduct = new OrderProduct
                    {
                        orderID = orderId,
                        producID = product.ID,
                        quantity = quantity,
                        unitPrice = product.unitPrice
                    };
                    orderProducts.Add(orderProduct);
                    db.OrderProducts.Add(orderProduct);
                }
                db.SaveChanges();
                return orderProducts;

            }
            catch (Exception)
            {
                return null;
            }
            
        }
    }
}