# Java异常处理

#### 异常概述及体系结构

异常:在Java语言中,将程序执行中发生的不正常情况称为异常(语法错误和逻辑错误不是异常)

**Error**:Java虚拟机无法解决的严重问题,一般不编写针对性代码进行处理(JVM内部错误,资源耗尽等严重情况)

如: 栈溢出[StackOverFlowError],堆溢出[OutOfMemoryError]

**Exception**:其他因编程错误或偶然的外在因素导致的一般性问题,可以使用针对性的代码进行处理

如:空指针访问,读取文件不存在,网络连接中断,数组下标越界等

对于上述错误一般有两种解决办法:

①遇到错误就终止程序的运行

②在编写程序时就考虑到错误的检测与提示以及错误的处理

捕获错误最理想的是在编译期间,但有的错误只有运行时才发生,故分为两种:编译时异常和运行时异常

#### 常见异常体系结构

> java.lang.Throwable
>
> ​	|----java.lang,Error
>
> ​	|----java.lang.Exception
>
> ​		|----编译时异常(checked)
>
> ​			|----IOException
>
> ​				|----FileNotFoundException
>
> ​			|----ClassNotFoundExpection
>
> ​		|----运行时异常(unchecked)
>
> ​			|----NullPointerException
>
> ​			|----ArrayIndexOutOfBoundsException
>
> ​			|----ClassCastException
>
> ​			|----NumberFormatException
>
> ​			|----InputMismatchException

#### 异常处理机制

抓抛模型:

抛:程序在正常执行的过程中,一旦出现异常,就会在异常代码处生成一个对应异常类对象,并将此对象抛出,一旦抛出对象以后,其后的代码不再执行

抓:可以理解为异常的处理方式,分为两种:

##### 机制一:try-catch-finally

```java
try{
//可能出现异常的代码
}catch(异常类型1 变量名1){
//处理异常的方式1
}catch(异常类型2 变量名2){
//处理异常的方式2
}catch(异常类型3 变量名3){
//处理异常的方式2
}...(异常类型n 变量名n){
//处理异常的方式n
}finally{
//一定会执行的代码
}
```

> 说明
> ①finally是可选的,且finally中的代码一定会被执行,即使catch中再次出现异常,try,catch中有return等情况也不受影响
>
> 像数据库连接,输入输出流,网络编程Socket等资源,JVM是不能自动回收的,需要手动进行资源释放,资源释放的操作就要声明在finally中
>
> ②使用try将可能出现异常的代码包起来,运行过程中一旦出现异常,就会生成一个对应异常类的对象,根据此对象,去catch中进行匹配
> ③一旦try中的异常对象匹配到某一个catch时,就进入catch中进行异常处理,处理完成后跳出当前try-catch结构,若有finally则执行
> ④catch中的异常类型,若没有子父类关系关系,则声明顺序不作要求,若满足子父类关系,则子类需写在父类的上方
> ⑤常用的异常对象处理的方式:
> 	1.String---getMessage()
>     2.printStackTrace();
> ⑥在try结构中声明的变量,出了try结构以后,就不能再被调用
>
> ⑦try-catch-finally结构可以嵌套

> 使用try-catch-finally处理编译时异常,使得程序在编译时就不再报错,但运行时仍可能报错,相当于讲一个编译时可能出现的异常延迟到运行时出现

> 开发中,由于运行时异常比较常见,通常不针对运行时异常使用try-catch-finally结构,针对编译时异常则一定要考虑异常处理

##### 机制二: throws + 异常类型

方法名 throws 异常类型{ 方法体 }

throws+异常类型写在方法的声明处,指明此方法执行时,可能会抛出的异常类型,一旦方法体执行时,出现异常,仍会在异常代码处生成一个异常类的对象,此对象满足throws后异常类型时,就会被抛出.异常代码后面的代码便不会再执行.

try-catch-finaly是真正的将异常处理掉,throws的方式只是将异常抛给了方法的调用者,并没有真正的处理异常

##### 开发中如何选择 使用try-catch-finally还是throws

1.如果父类中被重写的方法没有throws方式处理异常,则子类重写的方法也不能用throws,若子类中有异常,则必须用try-catch-finally方式

2.若方法A执行过程中先后调用了另外几个方法,且这几个方法是递进关系执行的,则建议这几个方法使用throws的方式进行处理,方法A则使用try-catch-finally方式进行处理

##### 手动抛出异常

异常对象的产生:

①系统自动生成的异常对象

②手动生成一个异常对象,并抛出(throw)

##### 用户自定义异常类

如何自定义异常类:

①继承于现有的异常结构:RuntimeException,Exception

②提供全局常量: serialVersionUID(long型唯一标识数据)

③提供重载的构造器





