using System;
using System.Collections.Generic;
using System.IO;
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
            return db.Products.ToList();
        }

        public ICollection<Product> GetProductByCaterory(int categoryId)
        {
            return db.Products
                .Where(product => product.categoryID == categoryId)
                .ToList();
        }

        public Product CreateProduct(string name, int categoryId, Decimal unitPrice, Uri image)
        {
            try
            {
                Product product = new Product
                {
                    name = name,
                    categoryID = categoryId,
                    unitPrice = unitPrice,
                    image = Path.GetFileName(image.AbsoluteUri)
                };
                db.Products.Add(product);
                db.SaveChanges();
                return product;
            }
            catch (Exception)
            {
                return null;
            }
        }

        public bool UpdateProduct(int id, string name, int? categoryId, decimal? unitPrice, Uri image)
        {
            try
            {
                int affectedRecord = 0;
                Product product = db.Products.Find(id);
                if (product != null)
                {
                    if (name != null)
                    {
                        product.name = name;
                    }
                    if (categoryId != null)
                    {
                        product.categoryID = categoryId;
                    }
                    if (unitPrice != null)
                    {
                        product.unitPrice = unitPrice.Value;
                    }
                    if (image != null)
                    {
                        product.image = Path.GetFileName(image.AbsoluteUri);
                    }

                    affectedRecord = db.SaveChanges();
                }
                return affectedRecord == 1;
            }
            catch (Exception)
            {
                return false;
            }
        }

        public bool RemoveProduct(int id)
        {
            throw new NotImplementedException();
        }
        
        
    }
}