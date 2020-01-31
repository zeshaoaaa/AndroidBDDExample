# language: zh-CN

功能: 登录

  @smoke
  @e2e

  场景大纲: 登录成功
    假如 用户进入了登录页
    当 用户输入了合法的手机号 <phone>
    并且 用户输入了合法的密码 <password>
    并且 用户点击了登录按钮
    那么 用户能看到“登录成功”的提示

    例子:
      | phone       | password |
      | 15200000000 | 123456   |

  场景大纲: 手机号长度有误
    假如 用户进入了登录页
    当 用户输入的手机号不等于 11 位 <phone>
    并且 用户输入了合法的密码 <password>
    并且 用户点击了登录按钮
    那么 用户应该看到“手机号有误，请重新确认”的提示

    例子:
      | phone         | password |
      | 666666        | 123456   |
      | 1521234567890 | 123456   |













