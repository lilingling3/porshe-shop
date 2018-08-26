// version 1.0.0
// 在项目里新建.eslintrc.js文件（注意.eslintrc.js和package.json应该处于平级位置）
// 拷贝下面内容至.eslintrc.js文件中，执行 eslint $your_dir_name  遍历检查文件夹下所有js文件

module.exports = {
    //此项是用来告诉eslint找当前配置文件不能往父级查找
    root: true,
    //此项是用来指定javaScript语言类型和风格，sourceType用来指定js导入的方式，默认是script，此处设置为module，指某块导入方式
    parserOptions: {
        sourceType: 'module',
        ecmaVersion: 6,
        ecmaFeatures: {
            jsx: true,
            experimentalObjectRestSpread: true
        }
    },

    //此项指定环境的全局变量，下面的配置指定为非浏览器环境
    env: {
        browser: false,
    },

    // https://github.com/feross/standard/blob/master/RULES.md#javascript-standard-style
    // 此项是用来配置标准的js风格，就是说写代码的时候要规范的写，如果你使用vs-code我觉得应该可以避免出错
    // extends: 'standard',//

    // 目前我们关闭上面的标准规则，启动如下的自定义规则
    // "off" -> 0 关闭规则
    // "warn" -> 1 开启警告规则
    //"error" -> 2 开启错误规则
    'rules': {
        'no-await-in-loop': 2,//禁止在循环中出现 await
        'no-cond-assign': 2,//禁止条件表达式中出现赋值操作符
        'no-dupe-args': 2,//禁止 function 定义中出现重名参数
        'no-dupe-keys': 2, //禁止对象字面量中出现重复的key
        'no-duplicate-case': 2,//禁止出现重复的 case 标签
        'no-empty': 1,//禁止出现空语句块
        'no-ex-assign': 2,//禁止对 catch 子句的参数重新赋值
        'no-func-assign': 2,//禁止对 function 声明重新赋值
        'no-prototype-builtins': 1,//禁止直接调用 Object.prototypes 的内置属性
        'no-unexpected-multiline': 1,//禁止出现令人困惑的多行表达式，等同于必须使用分号
        'use-isnan': 2,//要求使用 isNaN() 检查 NaN
        'accessor-pairs': 2,//强制 getter 和 setter 在对象中成对出现
        'default-case': 2,//要求 switch 语句中有 default 分支
        'dot-notation': 1,//  强制尽可能地使用点号
        'eqeqeq': 1,// 要求使用 === 和 !==
        'no-else-return': 1,// 禁止 if 语句中 return 语句之后有 else 块
        'no-empty-function': 2,// 禁止出现空函数
        'no-empty-pattern': 2,//禁止使用空解构模式
        'no-eval': 2,// 禁用 eval()
        'no-loop-func': 2,//禁止在循环中出现 function 声明和表达式
        'no-magic-numbers': 1,// 禁用魔术数字
        'no-new-wrappers': 2,// 禁止对 String，Number 和 Boolean 使用 new 操作符
        'no-param-reassign': 2,// 禁止对 function 的参数进行重新赋值
        'no-redeclare': 2,// 禁止多次声明同一变量
        'no-return-await': 1,// 禁用不必要的 return await
        'no-unused-expressions': 2,// 禁止出现未使用过的表达式
        'no-useless-return': 1,// 禁止多余的 return 语句
        'no-void': 2,// 禁用 void 操作符
        'no-with': 2,// 禁用 with 语句
        'require-await': 1,// 禁止使用不带 await 表达式的 async 函数
        'wrap-iife': 2,// 要求 IIFE 使用括号括起来
        'vars-on-top': 1,// 要求所有的 var 声明出现在它们所在的作用域顶部
        'no-path-concat': 2,//禁止使用___dirname, __filename直接做拼接，使用path.resolve 或 path.join 代替
    }
};