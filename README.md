# ZhuaTech AI Red Team｜企业 AI 红队安全平台

2026-10-23 功能更新：**红队场景覆盖矩阵**。按必测风险类别汇总检测成功与漏检，标出未测类别及高危失败，避免只用总体成功率掩盖覆盖缺口。处理已有测试结果，不生成攻击或发送测试流量。 [接口、操作与边界说明](docs/AI_PRACTICAL_UPGRADE_20261023.md)。

[简体中文](README.md) | [English](README.en.md)

由 **[知华科技（上海如静知华信息科技有限公司）](https://www.zhuatech.cn/)** 发布，用于在 AI 应用上线前执行可重复、可审计的安全测试。


## 本次升级：红队未缓解风险与复测闭环

按严重/一般问题分别核对发现、已缓解及独立复测通过数量，任何严重问题未闭环均阻断。它核对输入证据计数，不实际攻击系统。

新增已认证的结构化录入工作台（`/operations.html`），支持批量核算、逐条结果依据、分级复核和CSV/完整JSON导出。结果由真实后端算法计算，不是预置演示数字。所有数值精确传递，不调用外部模型。

这是**只读核算**：不写库、不审批、不自动执行财务、签署、销毁或设备控制。输入条件满足不是执行授权。[算法、输入边界与接口说明](docs/OPERATIONS-REVIEW.md)。
## 已实现的核心能力

- 检测中英文提示注入、系统提示泄露请求及指令优先级劫持。
- 检测 API Key、JWT、身份证、手机号、邮箱等敏感数据泄漏。
- 分析工具调用轨迹中的越权写入、任意命令、外发和凭据访问。
- 识别删除数据、转账、关闭安全控制等危险动作。
- 按严重度累计风险分，输出 `PASS / REMEDIATE / BLOCK`。
- 校验测试覆盖率、安全负责人复核和测试/审批职责分离。
- 为每个发现生成证据、规则编号和可执行修复建议。
- 活动请求幂等、结果持久化，管理员与审计员权限隔离。

## 接口

- `POST /api/ai-redteam/campaigns/run`：运行红队测试活动。
- `GET /api/ai-redteam/audits`：查询最近审计记录。

## 启动

```bash
cp .env.example .env
docker compose up --build
```

访问 `http://localhost:8094`。

> 只能对获得明确授权的模型和环境进行测试。本工程仅限个人非商业学习交流；企业使用和所有商业行为须取得上海如静知华信息科技有限公司书面授权，详见 [LICENSE](LICENSE)。

AI 安全评估、模型红队和私有化部署请联系[知华科技](https://www.zhuatech.cn/)。

## 微信咨询

商业授权、私有化部署或深度定制开发，可扫描下方二维码添加微信 `zhuatech` 或 `zhuatech2` 咨询。

| 微信 zhuatech | 微信 zhuatech2 |
| --- | --- |
| ![知华科技微信咨询 zhuatech](docs/images/zhuatech-wechat-consulting.png) | ![知华科技微信咨询 zhuatech2](docs/images/zhuatech-wechat-consulting-2.png) |
