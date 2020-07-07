# ChooseTitle
## 毕业生选题系统 
### 功能说明
* 管理员功能
    * 用户管理：学生信息、老师信息增加，用户数据删除
      重置学生、教师密码
      学生信息查询
    * 选题管理：毕业设计基本信息管理（查询届数，专业，人数，）
      定义选题开始时间和结束时间
      发布题目（题目总数大于等于学生人数才能发布），
      发布后学生才能选题
      题目查询，题目列表
      选题进度查询（已完成数、未完成数及名单）
    * 统计查询： 题目查询，题目列表
      选题进度查询（已完成数、未完成数及名单）
      分类统计（老师指导学生人数，各类题目数目）
* 学生用户功能
    * 个人信息管理：基本信息修改
    * 选题：题目查询，列表，选题（取消选题）
      选题状态查询（确定是否选题成功）
* 教师用户功能
    * 个人信息管理：修改个人信息
    * 题目管理：题目信息包括：题目名称、类型、主要开发工具、简介等。
      题目信息增加，
      题目信息维护（维护自己的题目；题目一旦被选，不能修改）
      选题状态查询，显示学生选择题目，学生基本信息
      选题确认，对学生的选题逐个确认；
### 功能介绍
“毕业设计选题管理系统 ”分为“前台”和“后台“两部分，前台是实现学生和教师浏览网站的功能，学生用户通过网上浏览教师的课题选择自己喜欢的课题，然后提交到后台数据库保存临时选题信息，待由老师确认学生选题信息，确认后存储到数据库，而教师则可以通过网上浏览申请课题，然后提交到后台数据库，待由管理员发布题目信息，设置选题时间，管理员能够查看有哪些学生选了题，哪些学生还没有选题等。
