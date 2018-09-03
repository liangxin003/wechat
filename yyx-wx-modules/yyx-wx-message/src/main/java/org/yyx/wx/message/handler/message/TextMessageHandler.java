package org.yyx.wx.message.handler.message;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.bussinessenum.MessageTypeEnum;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.reponse.message.TextMessageResponse;
import org.yyx.wx.commons.vo.pubnum.request.message.TextMessageRequest;
import org.yyx.wx.message.handler.AbstractMessageHandler;

/**
 * 文本消息处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-19:45
 */
public class TextMessageHandler extends AbstractMessageHandler {
    /**
     * TextMessageHandler日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TextMessageHandler.class);
    /**
     * 创建对象
     */
    private static final TextMessageHandler TEXT_MESSAGE_HANDLER = new TextMessageHandler();

    /**
     * 私有构造
     */
    private TextMessageHandler() {
    }

    /**
     * 获取对象
     *
     * @return 返回对象
     */
    public static TextMessageHandler getInstance() {
        return TEXT_MESSAGE_HANDLER;
    }

    /**
     * 文本消息处理器
     *
     * @param element 实际处理器要处理的数据
     * @return 处理后的消息
     */
    @Override
    protected TextMessageResponse dealTask(Element element) {
        LOGGER.info("[进入文本消息处理器]");
        // 强转成文本消息
        TextMessageRequest textMessageRequest = this.modelMethod(element);
        // 封装返回消息实体
        TextMessageResponse textMessageResponse = new TextMessageResponse();
        // 发送者是请求者对象中的接收者
        textMessageResponse.setFromUserName(textMessageRequest.getToUserName());
        // 接收者是请求者对象中的发送者
        textMessageResponse.setToUserName(textMessageRequest.getFromUserName());
        textMessageResponse.setContent("我是叶云轩的服务器，我返回的内容是：[你发的消息内容是]"
                + textMessageRequest.getContent() + "\n 今后补写富文本消息，<a href='https://my.oschina.net/yzwjyw/'>我的博客</a>");
        textMessageResponse.setMsgId(textMessageRequest.getMsgId());
        textMessageResponse.setCreateTime(System.currentTimeMillis());
        return textMessageResponse;
    }

    /**
     * 获取文本消息处理器级别
     *
     * @return 文本消息处理器级别
     */
    @Override
    protected String getHandlerLevel() {
        return MessageTypeEnum.text.toString();
    }

    /**
     * 模板方法
     *
     * @param element 微信请求过来的消息:xml
     * @return xml转换之后的实体对象 有可能为空
     */
    @Override
    protected TextMessageRequest modelMethod(Element element) {
        LOGGER.info("[微信请求过来的消息:xml格式数据] {}", element);
        TextMessageRequest textMessageRequest;
        try {
            textMessageRequest = WxXmlAndObjectUtil.xmlToObject(element, TextMessageRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        return textMessageRequest;
    }
}