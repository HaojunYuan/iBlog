const path = require('path')
const webpack = require('webpack')
const CompressionWebpackPlugin = require('compression-webpack-plugin')
const productionGzipExtensions = ['js', 'css'];

function resolve(dir) {
  return path.join(__dirname, dir)
}

let outputDir = './dist/web'
let assetsDir = './assets'      // ./dist/web/assets
let indexPath = './index.html' // ./dist/web/index.html

module.exports = {
  publicPath: '/', // 部署应用时的根路径(默认'/'),也可用相对路径(存在使用限制)
  outputDir: outputDir, // 运行时生成的生产环境构建文件的目录(默认''dist''，构建之前会被清除)
  assetsDir: assetsDir, //放置生成的静态资源(s、css、img、fonts)的(相对于 outputDir 的)目录(默认'')
  indexPath: indexPath, //指定生成的 index.html 的输出路径(相对于 outputDir)也可以是一个绝对路径。
  pages: {
    //pages 里配置的路径和文件名在你的文档目录必须存在 否则启动服务会报错
    index: {
      //除了 entry 之外都是可选的
      entry: 'src/main.js', // page 的入口,每个“page”应该有一个对应的 JavaScript 入口文件
      template: 'public/index.html', // 模板来源
      filename: 'index.html', // 在 dist/index.html 的输出
      title: 'Home', // 当使用 title 选项时,在 template 中使用：<title><%= htmlWebpackPlugin.options.title %></title>
      chunks: ['chunk-vendors', 'chunk-common', 'index'] // 在这个页面中包含的块，默认情况下会包含,提取出来的通用 chunk 和 vendor chunk
    }
  },
  runtimeCompiler: true,
  lintOnSave: false, // 是否在保存的时候检查
  productionSourceMap: false, // 生产环境是否生成 sourceMap 文件
  css: {
    extract: true, // 是否使用css分离插件 ExtractTextPlugin
    sourceMap: false, // 开启 CSS source maps
    loaderOptions: {
      less: {
        modifyVars: {
          // 'primary-color': '#1DA57A',
          // 'link-color': '#1DA57A',
          'border-radius-base': '2px',
        },
        javascriptEnabled: true
      }
    }, // css预设器配置项
    modules: false // 启用 CSS modules for all css / pre-processor files.
  },
  configureWebpack: config => {
    if (process.env.NODE_ENV === 'production') {
      // 配置参数详解
      // asset： 目标资源名称。 [file] 会被替换成原始资源。[path] 会被替换成原始资源的路径， [query] 会被替换成查询字符串。默认值是 "[path].gz[query]"。
      // algorithm： 可以是 function(buf, callback) 或者字符串。对于字符串来说依照 zlib 的算法(或者 zopfli 的算法)。默认值是 "gzip"。
      // test： 所有匹配该正则的资源都会被处理。默认值是全部资源。
      // threshold： 只有大小大于该值的资源会被处理。单位是 bytes。默认值是 0。
      // minRatio： 只有压缩率小于这个值的资源才会被处理。默认值是 0.8。
      config.plugins.push(
        // Ignore all locale files of moment.js
          new webpack.IgnorePlugin(/^\.\/locale$/, /moment$/),
          new CompressionWebpackPlugin({
            filename: '[path].gzip[query]',   // 提示compression-webpack-plugin@2.0.0的话filename改为asset
            algorithm: 'gzip',
            test: new RegExp('\\.(' + productionGzipExtensions.join('|') + ')$'),
            threshold: 10240,
            minRatio: 0.8
          })
      );
    }
    config.externals = {
      // 'VXETablePluginAntd': 'vxe-table-plugin-antd',
    }
  },
  //反向代理
  devServer: {
    //环境配置
    overlay: {
      warnings: false,
      errors: false
    },
    host: '127.0.0.1',
    port: 9999,
    https: false,
    hotOnly: false,
    open: false, //配置自动启动浏览器
    disableHostCheck: true, // 禁用webpack-dev-server安全验证
    proxy: {
      '/api/v1': {
        target: 'http://127.0.0.1:12000',
        changeOrigin: true,  //是否跨域
        pathRewrite: {
          '^/api/v1': '/api/v1'
        }
      },
    }
  },
  pluginOptions: {
  },
  // 关键配置：node_modules里面需要用babel-loader进行编译的包（node_modules的包如果没做 es6 => es5 转码则需要自己用babel-polyfill进行手动处理）
  // 如果IE下还跑不起来，控制台发现有其他es6语法的，尝试调试去找到底是哪个包
  transpileDependencies: [
    'ant-design-vue',
    'axios'
  ],
};

