# language: zh-CN
#noinspection CucumberTableInspection,NonAsciiCharacters,CucumberUndefinedStep
功能: 登录

  作为一名用户
  我想要登录到系统中
  以便可以查看我账户下的信息

  @smoke
  场景大纲: 账户信息验证
    假如用户进入了登录页
    当用户输入了手机号 <phone>
    并且用户输入了密码 <password>
    并且用户点击了登录按钮
    那么用户能看到提示 <toast>

    例子: 合法账户信息
      | phone       | password | toast |
      | 15266666666 | 123456   | 登录成功  |

    例子: 非法账户信息
      | phone         | password | toast       |
      | 666666        | 123456   | 手机号有误，请重新确认 |
      | 1521234567890 | 123456   | 手机号有误，请重新确认 |












