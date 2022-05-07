## 项目介绍

前端：xml

后端：java

匹配算法：python

### App结构介绍

![image-20220507145104103](D:\Note\img\image-20220507145104103.png)

#### 前端页面

![image-20220507150405935](D:\Note\img\image-20220507150405935.png)

#### 匹配算法

![image-20220507150541402](D:\Note\img\image-20220507150541402.png)





#### 后端

![image-20220507150719243](D:\Note\img\image-20220507150719243.png)

##### 数据库

数据库使用了基于SQLite的Room数据库。如果没有接触过的话，可以先去[使用 Room 将数据保存到本地数据库  | Android 开发者  | Android Developers (google.cn)](https://developer.android.google.cn/training/data-storage/room)看一下教程，其中有个Colab[Android Room with a View - Java (google.cn)](https://developer.android.google.cn/codelabs/android-room-with-a-view#9)实现了一个基于Room数据库的小程序。

![image-20220507151453077](D:\Note\img\image-20220507151453077.png)

数据库包含两个表，分别是 `user_Boat` 和 `user_Cargo`，都继承于 `user` ，用于存储船主和货主的信息。

![image-20220507152047805](D:\Note\img\image-20220507152047805.png)



## 搭建步骤

将项目克隆到本地，使用android studio打开该项目，第一次打开将会自动下载大量依赖包，等待依赖包下载完成后。

![image-20220507145337157](D:\Note\img\image-20220507145337157.png)

在该文件下找到 **python** 配置项，如下图：

![image-20220507145502003](D:\Note\img\image-20220507145502003.png)

更改 **buildPython** 为你的机器对应python路径即可。

之后点击 `Device Manager` 

![image-20220507145656165](D:\Note\img\image-20220507145656165.png)

运行 `Nexus 5 API 32`，再点击运行

![image-20220507145739953](D:\Note\img\image-20220507145739953.png)

即可在虚拟机上查看App当前效果。

成功运行结果如图：

![image-20220507145855264](D:\Note\img\image-20220507145855264.png)

