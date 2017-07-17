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

        public Product Find(int? id)
        {
            Product product = null;
            product = db.Products.Find(id);
            return product;
        }

        public Product CreateProduct(string name, int categoryId, Decimal unitPrice, string filename)
        {
            try
            {
                Product product = new Product
                {
                    name = name,
                    categoryID = categoryId,
                    unitPrice = unitPrice,
                    image = filename
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

        /// <summary>
        /// Update product with [id] with giving value
        /// The value that not want to be update can be left null
        /// Set use default value if want to remove it
        /// </summary>
        /// <param name="id"></param>
        /// <param name="name"></param>
        /// <param name="categoryId"></param>
        /// <param name="unitPrice"></param>
        /// <param name="image"></param>
        /// <returns></returns>
        public bool UpdateProduct(int? id, string name, int? categoryId, decimal? unitPrice, string image, bool? isRemoved)
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
                        product.image = image;
                    }
                    if (isRemoved.HasValue)
                    {
                        if (isRemoved.Value)
                        {
                            product.isRemoved = true;
                        }
                        else
                        {
                            product.isRemoved = false;
                        }
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
            bool isSuccess = false;
            try
            {
                Product product = db.Products.Find(id);
                if (product != null)
                {
                    db.Products.Remove(product);
                    int affectedRecord = db.SaveChanges();
                    isSuccess = affectedRecord == 1;
                }
            }catch(Exception){}
            return isSuccess;
        }
    }
}