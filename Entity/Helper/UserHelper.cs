using System;
using System.Collections.Generic;
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

        public bool IsAdmin(string username)
        {
            return username.Equals("Admin");
        }

        public bool AuthenticateEmployee(string username, string password)
        {
            bool isSuccess = db.Users
                .Where(user => user.username.Equals(username))
                .Where(user => user.password.Equals(password))
                .Any(user => user.isEmployee == true);
            return isSuccess;
        }

        public User GetUser(string username, string password)
        {
            User found = db.Users
                .Where(user => user.username.Equals(username))
                .FirstOrDefault(user => user.password.Equals(password));
            return found;
        }

        public ICollection<User> GetAllUser()
        {
            return db.Users.ToList();
        }

        public User CreateUser(string username, string password, string phonenumber, bool isEmployee)
        {
            try
            {
                User newUser = null;
                bool isDuplicatedUsername = db.Users
                    .Any(user => user.username == username);
                if (!isDuplicatedUsername)
                {
                    newUser = new User
                    {
                        username = username,
                        password = password,
                        phonenumber = phonenumber,
                        balance = 0,
                        isEmployee = isEmployee
                    };
                    db.Users.Add(newUser);
                    db.SaveChanges();
                }
                return newUser;
            }
            catch (Exception)
            {
                return null;
            }
        }

        /// <summary>
        ///     Update a value that is not null
        ///     when want to delete a value(ex:phone number) set it to emptry string (ex: "") 
        /// </summary>
        /// <param name="id"></param>
        /// <param name="phonenumber"></param>
        /// <param name="isEmployee"></param>
        /// <param name="password"></param>
        /// <returns></returns>
        public bool UpdateUser(int id, string phonenumber, bool? isEmployee, string password)
        {
            try
            {
                User user = db.Users.Find(id);
                if (user != null)
                {
                    if (phonenumber != null)
                    {
                        user.phonenumber = phonenumber;
                    }
                    if (password != null)
                    {
                        user.password = password;
                    }
                    if (isEmployee != null)
                    {
                        user.isEmployee = isEmployee.Value;
                    }
                }
                db.SaveChanges();
                return true;
            }
            catch (Exception)
            {
            }
            return false;
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

            User foundUser = db.Users.Find(userId);
            if (foundUser?.balance - balance > 0)
            {
                foundUser.balance -= balance;
                db.SaveChanges();
                isSuccess = true;
            }

            return isSuccess;
        }

        public decimal GetBalance(string username, string password)
        {
            var foundUser = db.Users
                .Where(user => user.username.Equals(username))
                .FirstOrDefault(user => user.password.Equals(password));

            if (foundUser?.balance != null)
            {
                Decimal balance = foundUser.balance.Value;
                return balance;
            }

            throw new Exception($"User not existed");
        }
    }
}