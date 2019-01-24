package week.day0121;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Menu {
    Operator mOperator;

    public void List() throws Exception {
        mOperator = new Operator();
        Scanner scanner = new Scanner(System.in);
        int nValue = 0;
        while (true) {
            System.out.println("1.增加数据");
            System.out.println("2.删除数据");
            System.out.println("3.修改数据");
            System.out.println("4.查找数据");
            System.out.println("5.显示所有数据");
            System.out.println("0.退出系统");
            System.out.println("请输入选择项：");
            nValue = scanner.nextInt();
            if (nValue == 0) {
                break;
            } else {
                this.Operator(nValue);
            }
        }
        System.exit(1);
    }

    public void Operator(int nValue) throws Exception {
        switch (nValue) {
            case 1: {
                BufferedReader buf = null;
                buf = new BufferedReader(new InputStreamReader(System.in));
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入用户名：");
                String strValue = buf.readLine();
                System.out.println("请输入年龄：");
                int nAge = scanner.nextInt();
                Person nPerson = new Person(strValue, nAge);
                if (mOperator.Add(nPerson) == false) {
                    System.out.println("添加失败.");
                } else {
                    System.out.println("添加成功.");
                }
                break;
            }
            case 2: {
                BufferedReader buf = null;
                buf = new BufferedReader(new InputStreamReader(System.in));
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入用户名：");
                String strValue = buf.readLine();
                if (mOperator.Del(strValue)) {
                    System.out.println("删除成功.");
                } else {
                    System.out.println("删除失败.");
                }
                break;
            }
            case 3: {
                BufferedReader buf = null;
                buf = new BufferedReader(new InputStreamReader(System.in));
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入用户名：");
                String strValue = buf.readLine();
                System.out.println("请输入年龄：");
                int nAge = scanner.nextInt();
                Person nPerson = new Person(strValue, nAge);
                if (mOperator.Modi(nPerson)) {
                    System.out.println("修改成功.");
                } else {
                    System.out.println("修改失败.");
                }
                break;
            }
            case 4: {
                BufferedReader buf = null;
                buf = new BufferedReader(new InputStreamReader(System.in));
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入用户名：");
                String strValue = buf.readLine();
                if (mOperator.Find(strValue)){
                    System.out.println("查找成功.");
                }else{
                    System.out.println("查找失败.");
                }
                break;
            }
            case 5: {
                if (mOperator.DisplayAll()){
                    System.out.println("显示成功.");
                }else{

                    System.out.println("显示失败.");
                }
                break;
            }
            default: {
                System.out.println("No Find Data Value:" + nValue);
            }
        }
    }
}

