using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace Entity.JsonModel
{
    public class UserJsonModel
    {
        [JsonProperty("ID")]
        public int ID { get; set; }
        [JsonProperty("username")]
        public String username { get; set; }
        //public String password { get; set; }
        [JsonProperty("phone")]
        public String phonenumber { get; set; }
        [JsonProperty("balance")]
        public decimal? balance { get; set; }

        public static implicit operator UserJsonModel(User user)
        {
            return new UserJsonModel
            {
                ID = user.ID,
                username = user.username,
                phonenumber = user.phonenumber,
                balance = user.balance
            };
        }
    }
}