根目录：net.csdn

1.启动类（CsdnApplication.java）推荐放在根目录net.csdn包下
2.实体类（domain）
    A: net.csdn.domain（jpa项目）
    B: net.csdn.pojo（mybatis项目）
3.数据接口访问层(Dao)
    A: net.csdn.repository（jpa项目）
   B: net.csdn.mapper（mybatis项目）
4.数据服务接口层（Service）推荐：net.csdn.service
5.数据服务实现层（Service Implements）推荐：net.csdn.service.impl
    ——使用idea的同学推荐使用net.csdn.serviceImpl目录
6.前端控制器层（Controller）推荐：net.csdn.controller
7.工具类库（utils）推荐：net.csdn.utils
8.配置类（config）推荐：net.csdn.config
9.数据传输对象（dto）推荐：net.csdn.dto
    ——数据传输对象（Data Transfer Object）用于封装多个实体类（domain）之间的关系，不破坏原有的实体类结构
