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
                .Any(user => user.password.Equals(password));
            return isSuccess;
        }

        public bool AuthenticateEmployee(string username, string password)
        {
            bool isSuccess = db.Users
                .Where(user => user.username.Equals(username))
                .Where(user => user.password.Equals(password))
                .Any(user => user.isEmployee == true);
            return isSuccess;
        }

        public bool CreateUser(string username, string password, string phonenumber, bool isEmployee)
        {
            bool isSuccess = false;
            bool isDuplicatedUsername = db.Users
                .Any(user => user.username == username);
            if (!isDuplicatedUsername)
            {
                User newUser = new User
                {
                    username = username,
                    password = password,
                    phonenumber = phonenumber,
                    balance = 0,
                    isEmployee = isEmployee
                };
                db.Users.Add(newUser);
                int affectedRecord = db.SaveChanges();
                isSuccess = affectedRecord == 1;
            }

            return isSuccess;
        }

        public bool AddBalance(int userId, decimal balance)
        {
            bool isSuccess = false;

            var find = db.Users.Find(userId);
            if (find != null)
            {
                find.balance += balance;
                db.SaveChanges();
                isSuccess = true;
            }

            return isSuccess;
        }

        public bool RemoveBalance(int userId, decimal balance)
        {
            bool isSuccess = false;

            var find = db.Users.Find(userId);
            if (find != null)
            {
                find.balance -= balance;
                db.SaveChanges();
                isSuccess = true;
            }

            return isSuccess;
        }

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