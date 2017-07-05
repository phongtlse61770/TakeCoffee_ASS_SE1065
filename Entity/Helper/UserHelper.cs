using System;
using System.Linq;

namespace Entity.Helper
{
    public class UserHelper : BaseEntityHelper
    {
        public bool Authenticate(string username, string password)
        {
            bool isSuccess = db.Users
                                 .Where(user => user.username.Equals(username))
                                 .Count(user => user.password.Equals(password)) == 1;
            return isSuccess;
        }

        public bool AuthenticateAdmin(string username, string password)
        {
            bool isSuccess = db.Users
                                 .Where(user => user.username.Equals(username))
                                 .Where(user => user.password.Equals(password))
                                 .Count(user => user.isAdmin == true) == 1;
            return isSuccess;
        }

        public bool CreateUser(string username, string password, string phonenumber)
        {
            bool isSuccess = false;
            bool isDuplicatedUsername = db.Users
                                            .Count(user => user.username.Equals(username)) > 0;
            if (!isDuplicatedUsername)
            {
                User newUser = new User
                {
                    username = username,
                    password = password,
                    phonenumber = phonenumber,
                    balance = 0,
                    isAdmin = false,
                };
                db.Users.Add(newUser);
                int affectedRecord = db.SaveChanges();
                isSuccess = affectedRecord == 1;
            }

            return isSuccess;
        }

//        public bool AddBalance(int userId , decimal balance)
//        {
//            
//        }
//
//        public bool RemoveBalance(int userId, decimal balance)
//        {
//
//        }

        public decimal GetBalance(int id)
        {
            var user = db.Users.Find(id);
            if (user?.balance != null)
            {
                Decimal balance = user.balance.Value;
                return balance;
            }
            throw new Exception($"User with id {id} not existed");
        }
    }
}