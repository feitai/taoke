use yc125res;

insert into resadmin(raname,rapwd) values( 'a','0cc175b9c0f1b6a831c399e269772661');


insert into resuser( username, pwd,email) values( 'a', '$2a$10$WLM6zEXiDDPLx1HN.QxvA.IXM15gUKDOikMOnC6yUy6ag/7XmN7Ca','a@163.com');
insert into resuser( username, pwd,email) values( 'b', '$2a$10$2O4BYB9iYNBRkHDGgW7a1.ETWwGyYTzekh3OmNKySINnd8b5rrK3q','b@163.com');


insert into resfood(fname,normprice,realprice,detail, fphoto)  values('素炒莴笋丝',22.0,20.0,'营养丰富','500008.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('蛋炒饭',22.0,20.0,'营养丰富','500022.jpg');
insert into resfood( fname,normprice,realprice,detail, fphoto)  values('酸辣鱼',42.0,40.0,'营养丰富','500023.jpg');
insert into resfood( fname,normprice,realprice,detail, fphoto)  values('鲁粉',12.0,10.0,'营养丰富','500024.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('西红柿蛋汤',12.0,10.0,'营养丰富','500025.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)   values('炖鸡',102.0,100.0,'营养丰富','500026.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('炒鸡',12.0,10.0,'营养丰富','500033.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)   values('炒饭',12.0,10.0,'营养丰富','500034.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)   values('手撕前女友',12.0,10.0,'营养丰富','500035.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('面条',12.0,10.0,'营养丰富','500036.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('端菜',12.0,10.0,'营养丰富','500038.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)   values('酸豆角',12.0,10.0,'营养丰富','500041.jpg');

insert into resorder(userid,address,tel,ordertime,deliverytime,ps,status)
values( 1,'湖南省衡阳市','13878789999',now(),now(),'送餐上门',0);
insert into resorderitem(roid,fid,dealprice,num)
values( 1,1,20,1);
insert into resorderitem(roid,fid,dealprice,num)
values( 1,2,20,1);

commit;



