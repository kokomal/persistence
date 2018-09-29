## OneToOne FK
这里book和book_detail都是一对一的关系，book内嵌了book_detail_id外键约束到book_detail  
book [book_detail_id] ------------1to1-------------> book_detail[id]  

在Book类的BookDetail成员变量上加注解  
@OneToOne(cascade = CascadeType.ALL)  
@JoinColumn(name = "book_detail_id")  
表明这个成员变量以column id为book_deital_id外联到BookDetail这个pojo对应的book_detail表

在BookDetail类的Book成员变量上加注解
@OneToOne(mappedBy = "bookDetail")  
表明这个是一对一的被动关系，mappedBy可选,反向指明了被主导的域（本例是指Book类的bookDetail成员变量）  
不失一般性，如果mappedBy不选，如果将来Book类有多个BookDetail的实例就不好办了  
最后，在book_detail表中，没有book字段，完全无感book的存在  