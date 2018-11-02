# Lock
分布式锁以及秒杀
 
redislock包下实现的分布式锁。具体是使用的是redis中的setnx来确保唯一性。

seckill包下实现的是秒杀，需要在redis中  set goods 数量 。增加物品的数量。

因为是简单的实现，所以其中可能有遗漏的没有考虑到的部分。

一起探讨，一起进步！！！
