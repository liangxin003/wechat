package org.yyx.wx.message.handler;

import org.yyx.wx.commons.vo.pubnum.reponse.message.BaseMessageResponse;
import org.yyx.wx.commons.vo.pubnum.request.BaseMessageAndEventRequestAndResponse;

/**
 * 消息处理接口
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/6-12:00
 */
public interface IMessageHandler {

    /**
     * 处理消息的方法
     *
     * @param baseMessageAndEventRequest 微信推送过来的事件实体
     * @return 要通过服务器向公众号给用户发送的消息
     */
    BaseMessageResponse dealMessage(BaseMessageAndEventRequestAndResponse baseMessageAndEventRequest);
}