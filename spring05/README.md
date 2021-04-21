scope singleton与prototype区别

演示bean生命周期

tips:
因为Service中的Dao类是稳定的(恒定不变的), 所以设置为单例没问题

如果一个类中某个属性是会改变的, 要设置成多例