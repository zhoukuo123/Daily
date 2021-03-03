package com.zk.jdbc.hrapp;

import com.zk.jdbc.hrapp.command.Command;
import com.zk.jdbc.hrapp.command.PstmtQueryCommand;
import com.zk.jdbc.hrapp.command.QueryCommand;

import java.util.Scanner;

public class HumanResourceApplication {
    public static void main(String[] args) {
        System.out.println("1-查询部门员工");
        Scanner in = new Scanner(System.in);
        int cmd = in.nextInt();
        switch (cmd) {
            case 1: // 查询部门员工
                Command command = new PstmtQueryCommand();
                command.execute();
                break;
        }

    }
}
