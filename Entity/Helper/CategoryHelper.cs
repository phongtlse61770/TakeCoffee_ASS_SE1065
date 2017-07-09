using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entity.Helper
{
    public class CategoryHelper : BaseEntityHelper
    {
        public ICollection<Category> GetAllCategory()
        {
            return db.Categories.ToList();
        }

        public Category InsertCategory(string name)
        {
            try
            {
                Category category = new Category
                {
                    name = name
                };
                db.Categories.Add(category);
                db.SaveChanges();
                return category;
            }
            catch (Exception)
            {
                return null;
            }
        }

        public bool UpdateCategory(int id, string name)
        {
            try
            {
                var find = db.Categories.Find(id);
                if (find != null)
                {
                    find.name = name;
                }
                db.SaveChanges();
                return true;
            }
            catch (Exception)
            {
                return false;
            }
        }

        public bool DeleteCategory(int id)
        {
            try
            {
                var find = db.Categories.Find(id);
                if (find != null)
                {
                    db.Categories.Remove(find);
                }
                db.SaveChanges();
                return true;
            }
            catch (Exception)
            {
                return false;
            }
            
        }
    }
}