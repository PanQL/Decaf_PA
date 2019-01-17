## Decaf PA 2 Report  

#####  计61 潘庆霖 2016011388  

### 1、类的浅复制  

* 修改Tree.java：增加了ScopyClass类

* 修改TypeCheck.java: 增加了对ScopyClass语法节点的类型检查 ，具体包括对scopy表达式中被赋值的变量标识符进行检查，查看是否是class类型；对用来赋值的表达式进行类型检查，查看是否是class类型；然后比较二者的类型是否相等。
### 2、sealed的支持  

* 修改Tree.java：在ClassDef中添加布尔类型的成员isSealed，默认为false，在语法树建立的时候设置，用来标记是否是设置为sealed的类。

* 修改BuildSym.java中的visitTopLevel函数：第一遍定义所有class的时候增加对出现的sealed类的name的记录，以及第二遍检查所有class继承情况的时候，对继承的父类是否sealed类的检查  。
### 3、支持串行条件卫士  
* 修改TypeCheck.java: 增加对串行条件卫士的节点ForeachStmt的静态类型检查，以及对分支语句Tree.IfSubstmt的静态类型检查 。 

### 4、支持简单的类型推导  
* 修改Tree.java：

  * 为LValue类型添加了公共成员name，String类型。
  * 添加了语法节点类TypeVar，继承TypeLiteral，作为一种新的类型。
  * 添加了语法节点类VarIdent，继承LValue，作为类似var x = 1；中对应的左值节点。
  * 添加了语法节点类BoundVariable，继承VarDef，用来表示Foreach中var类型的循环变量声明。
* 修改BuildSym.java：
  * 添加了对类型TypeVar的扫描。
  * 添加了对Assign、VarIdent、BoundVariable语法节点类型的扫描。
* 修改BaseType.java：添加了一种基本类型叫VAR，字符串描述是“unknown”，作为var定义的变量的初始类型。
* 修改TypeCheck.java：

  * 添加了visitAssign中对var类型变量的实际类型的推导和检查。

  * 添加了visitForeacStmt中对var类型循环变量的推导和检查。  
### 5、支持三种数组操作  
#### 1）数组初始化常量表达式  

* 修改TypeCheck.java：添加对语法节点ArrayRepeat的静态类型检查

#### 2）数组下标动态访问表达式  

* 修改TypeCheck.java:  添加对语法节点ArrayDefault的静态类型检查，具体包括对对应的数组的类型检查，对下标的类型检查， 对default后面的表达式的类型检查。

#### 3）数组迭代语句foreach  

* 修改Tree.java:添加BoundVariable，作为循环中的迭代变量的对应语法节点。
* 修改BuildSym.java：添加对ForeachStmt的符号表构建，BoundVariable的变量符号表项构建。
* 修改TypeCheck.java：添加对ForeachStmt中的BoundVariable的类型检查，对数组的类型检查，对布尔表达式的类型检查。