package rpc;


import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * 响应结果生成工具
 *
 * @author
 * @date 2018/06/09
 */
public class ResultGenerator {

    private static final String DEFAULT_OK_MESSAGE = "请求成功";
    private static final String DEFAULT_UNAUTHORIZED_MESSAGE = "认证失败";
    private static final String DEFAULT_METHOD_NOT_ALLOWED_MESSAGE = "请求方法不正确";

    public static Result genOkResult() {
        return new Result
            .Builder(HttpResponseStatus.OK.code())
            .msg(DEFAULT_OK_MESSAGE)
            .build();
    }

    public static Result genOkResult(final Object data) {
        return new Result
            .Builder(HttpResponseStatus.OK.code())
            .msg(DEFAULT_OK_MESSAGE)
            .data(data)
            .build();
    }

    public static Result genFailedResult(final String msg) {
        return new Result
            .Builder(HttpResponseStatus.BAD_REQUEST.code())
            .msg(msg)
            .build();
    }

    public static Result genMethodErrorResult() {
        return new Result
            .Builder(HttpResponseStatus.METHOD_NOT_ALLOWED.code())
            .msg(DEFAULT_METHOD_NOT_ALLOWED_MESSAGE)
            .build();
    }

    public static Result genUnauthorizedResult() {
        return new Result
            .Builder(HttpResponseStatus.UNAUTHORIZED.code())
            .msg(DEFAULT_UNAUTHORIZED_MESSAGE)
            .build();
    }

    public static Result genUnauthorizedResult(final String msg) {
        return new Result
            .Builder(HttpResponseStatus.UNAUTHORIZED.code())
            .msg(msg)
            .build();
    }

    public static Result genInternalServerErrorResult(final String url) {
        return new Result
            .Builder(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
            .msg(url)
            .build();
    }

    public static Result getAccessDeniedErrorResult(final String msg) {
        return new Result
            .Builder(HttpResponseStatus.FORBIDDEN.code())
            .msg(msg)
            .build();
    }
}
