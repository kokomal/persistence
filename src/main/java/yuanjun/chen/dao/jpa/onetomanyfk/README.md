## OneToMany FK
这里是经典的一对多关系  
一个公司Company对应多个员工Employee  
employee(company_id) -------nto1------------> company(id)

Company作为oneToMany的主导方和关系拥有者，需要添加注解  
@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
mappedBy代表Employee中的company字段， fetch代表是否懒加载  
注意FetchType要设置成Eager，否则无法打印出来  

Employee作为ManyToOne的主导方和关系拥有者，需要在Company字段上添加注解  
@ManyToOne  
@JoinColumn(name = "company_id")  
代表多对一关系的拥有者，代表employee的company_id外联到了Company实体   
