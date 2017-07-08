using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Entity.JsonModel;
using Newtonsoft.Json.Linq;

namespace Entity.Helper
{
    public class ProductHelper : BaseEntityHelper
    {
        public ICollection<Product> GetAllProduct()
        {
            return db.Products.ToList<Product>();
        }

        public ICollection<Product> GetProductByCaterory(int categoryId)
        {
            return db.Products
                .Where(product => product.categoryID == categoryId)
                .ToList<Product>();
        }

        public bool CreateProduct(string name,int categoryID, Decimal unitPrice, Uri image)
        {
            Product product = new Product
            {
//                name = 
            };
        }
    }
}