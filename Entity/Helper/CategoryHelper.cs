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
    }
}
