using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entity.Helper
{
    public class BaseEntityHelper : IDisposable
    {
        protected TakeCoffeeEntities db;

        public BaseEntityHelper()
        {
            db = new TakeCoffeeEntities();
        }
        
        public void Dispose()
        {
            db?.Dispose();
        }
    }
}
