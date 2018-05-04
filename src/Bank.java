import javax.management.Query;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class Bank {
    //银行账户类
    public static class BankAccount{
        String userName;
        String passWord;
        double balance;
        //构造函数
        BankAccount()
        {
            userName = "Unknown";
            passWord = "";
            balance = 0;
        }
        BankAccount(String userName,String passWord,double balance)
        {
            this.balance = balance;
            this.userName = userName;
            this.passWord = passWord;
        }
        //存取钱方法

        public void SaveMoney(double savenum)
        {
            this.balance+=savenum;
            System.out.println("当前余额" + balance + "元.");
        }

        public void WithDrawMoney(double withdrawmoney)
        {
            if(balance < withdrawmoney || balance < 0)
            {
                balance-=withdrawmoney;
                System.out.println("当前余额" + balance + "元.");
            }
            else
            {
                System.out.println("当前余额不足.");
            }
        }

        public void QueryBalance()
        {
            System.out.println("当前余额" + balance + "元.");
        }


    }
    //银行类

    public static class BankCompany
    {
        String bankName;
        ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

        //构造函数
        BankCompany(String bankName)
        {
            this.bankName = bankName;
        }
        //开户

        public void CreateAccount(String username,String password,double balance)
        {
            BankAccount p=new BankAccount(username,password,balance);
            accounts.add(p);
        }

        //查找账户
        public boolean findAccount(String username,BankAccount queryresult)
        {
            for(BankAccount bk:accounts)
            {
                if(bk.userName == username)
                {
                    queryresult = bk;
                    return true;
                }
            }
            return false;
        }


    }

    public class BankTest {

        BankCompany testcompany;

        public void setUp() throws Exception {
            testcompany = new Bank.BankCompany("ICBC");
            testcompany.CreateAccount("David","123456",200);
        }
        //其他测试函数，用assert实现

    }


    public static void main(String args[])
    {
        //实际应用
        BankCompany Banker=new BankCompany("CCB");
        Banker.CreateAccount("David","123456",200);
        BankAccount t = new BankAccount();
        //查找账户
        if(Banker.findAccount("David",t) == true)
        {
            t.QueryBalance();
            t.SaveMoney(800);
            t.WithDrawMoney(300);
            //t.WithDrawMoney(10000);
        }

    }

}
