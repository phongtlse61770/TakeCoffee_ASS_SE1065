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

        public ICollection<Product> GetProductByCaterory(int categoryID)
        {
            return db.Products
                .Where(product => product.categoryID == categoryID)
                .ToList<Product>();
        }
    }
}