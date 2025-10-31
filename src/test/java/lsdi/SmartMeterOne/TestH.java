package lsdi.SmartMeterOne;

import lsdi.SmartMeterOne.service.HolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class TestH {
    @Autowired
    HolderService holderService;

    @Test
    public void handle() {
        String topic = "connections";
        String payload="{\"state\": \"active\", \"created_at\": \"2025-10-28T18:17:17.709887Z\", \"updated_at\": \"2025-10-28T18:17:18.026837Z\", \"connection_id\": \"98e58ba2-8424-4434-947b-e33f13c215a0\", \"my_did\": \"SUfjBkaSqRkLFwgmsPKaX3\", \"their_did\": \"BL64N2HTmXzZa87Yd8eoBz\", \"their_label\": \"citizen.agent\", \"their_role\": \"invitee\", \"connection_protocol\": \"didexchange/1.0\", \"rfc23_state\": \"completed\", \"invitation_key\": \"2LL4G8GkQGCPrmapNS6ASuGEU7ncWkXAkpnasEprgXNS\", \"request_id\": \"693713b6-b13c-4098-8d6b-9518dd819efc\", \"accept\": \"auto\", \"invitation_mode\": \"once\"}";

        holderService.handleEvent(topic, payload);
    }

    @Test
    public void getAcccess() {
        String topic = "connections";
        String payload="{\"state\": \"active\", \"created_at\": \"2025-10-28T18:17:17.709887Z\", \"updated_at\": \"2025-10-28T18:17:18.026837Z\", \"connection_id\": \"98e58ba2-8424-4434-947b-e33f13c215a0\", \"my_did\": \"SUfjBkaSqRkLFwgmsPKaX3\", \"their_did\": \"BL64N2HTmXzZa87Yd8eoBz\", \"their_label\": \"citizen.agent\", \"their_role\": \"invitee\", \"connection_protocol\": \"didexchange/1.0\", \"rfc23_state\": \"completed\", \"invitation_key\": \"2LL4G8GkQGCPrmapNS6ASuGEU7ncWkXAkpnasEprgXNS\", \"request_id\": \"693713b6-b13c-4098-8d6b-9518dd819efc\", \"accept\": \"auto\", \"invitation_mode\": \"once\"}";

        holderService.handleEvent(topic, payload);
    }

    @Test
    public void handlefilter() {
        String topic = "connections";
        String payload="{\n" +
                "  \"state\": \"done\",\n" +
                "  \"created_at\": \"2025-10-31T00:24:18.614740Z\",\n" +
                "  \"updated_at\": \"2025-10-31T00:25:22.438575Z\",\n" +
                "  \"trace\": false,\n" +
                "  \"pres_ex_id\": \"f2b3052e-dbe1-4bc3-8f04-f863d86278cb\",\n" +
                "  \"connection_id\": \"f68a78d6-79c3-44a1-ba78-6960cca088d7\",\n" +
                "  \"thread_id\": \"fea5bd8e-6d72-4b73-9735-0fa7fbc9f682\",\n" +
                "  \"initiator\": \"self\",\n" +
                "  \"role\": \"verifier\",\n" +
                "  \"pres_request\": {\n" +
                "    \"@type\": \"https://didcomm.org/present-proof/2.0/request-presentation\",\n" +
                "    \"@id\": \"fea5bd8e-6d72-4b73-9735-0fa7fbc9f682\",\n" +
                "    \"will_confirm\": true,\n" +
                "    \"formats\": [\n" +
                "      {\n" +
                "        \"attach_id\": \"indy\",\n" +
                "        \"format\": \"hlindy/proof-req@v2.0\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"request_presentations~attach\": [\n" +
                "      {\n" +
                "        \"@id\": \"indy\",\n" +
                "        \"mime-type\": \"application/json\",\n" +
                "        \"data\": {\n" +
                "          \"base64\": \"eyJuYW1lIjogIkFwcGxpY2F0aW9uIFByb29mIFJlcXVlc3QiLCAidmVyc2lvbiI6ICIxLjAiLCAicmVxdWVzdGVkX2F0dHJpYnV0ZXMiOiB7ImF0dHIiOiB7Im5hbWVzIjogWyJwZXJtaXNzaW9uX2xpc3QiLCAiZnVsbF9uYW1lIl0sICJyZXN0cmljdGlvbnMiOiBbeyJpc3N1ZXJfZGlkIjogIkNHdjlkOEhFMkZrZWsyZjFweTJqN2cifV19fSwgInJlcXVlc3RlZF9wcmVkaWNhdGVzIjoge30sICJub25jZSI6ICIxMTIxNTcxODU4MDU4Njg3MjI4OTkxOTk4In0=\"\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"pres\": {\n" +
                "    \"@type\": \"https://didcomm.org/present-proof/2.0/presentation\",\n" +
                "    \"@id\": \"f7c5abb6-0354-4924-a200-9faa40a3cb9a\",\n" +
                "    \"~thread\": {\n" +
                "      \"thid\": \"fea5bd8e-6d72-4b73-9735-0fa7fbc9f682\"\n" +
                "    },\n" +
                "    \"formats\": [\n" +
                "      {\n" +
                "        \"attach_id\": \"indy\",\n" +
                "        \"format\": \"hlindy/proof@v2.0\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"presentations~attach\": [\n" +
                "      {\n" +
                "        \"@id\": \"indy\",\n" +
                "        \"mime-type\": \"application/json\",\n" +
                "        \"data\": {\n" +
                "          \"base64\": \"eyJwcm9vZiI6IHsicHJvb2ZzIjogW3sicHJpbWFyeV9wcm9vZiI6IHsiZXFfcHJvb2YiOiB7InJldmVhbGVkX2F0dHJzIjogeyJmdWxsX25hbWUiOiAiMTA2NTU2NDgwMzE4MjkwNDg1OTA5MTgyNzk3MzkyNTEwNjc1Mzg2NDExNjk4MzE2MTgxOTg0NjgyNzAyMzIyNjQ2Nzg5NjkwMTg5NjgzIiwgInBlcm1pc3Npb25fbGlzdCI6ICI0MjY2MzcyNDk3MTM2MDY5NjY1MjA2MTY2MjIwNDEyNjM4Njg2NDMwNzc0MTQ4MTgwODQzNDE3MjI2NzQwNTMyNzYxMDA3MzkyMDc2NCJ9LCAiYV9wcmltZSI6ICI1ODU5ODA1ODg2OTExOTcwOTY3NjMwNjg2NDgwNjM0Njc2ODEyMjI2NzA4NDM0NjU1NzUyMTg3NDI5NDg1NDcwNTA5Mjk1MzU2MzgyOTc4NTQ0NzUxNjc1Nzk2MTY4ODY0NDQ2OTM4NDU1ODkzNTA5NTU4MzkyODY3MjY5NjkxODM3NTU0NDAyOTA4OTg1NzQzMTI1NzY2NTAxNjU0MTkxODQ2Nzk5MDM2MjE2NDg0MDY4NDMxNDYxNTM0NTU0ODU3MzU0MDUyODE0ODM2MjUxMjQ4NDM2NjU4Njk0MTE3MTk0NDQ4ODg2NTMyMzMyNDg1MzA1MTExOTE2NzI4MjU4MzQ0MjEyMDMyMDc1Mjc2MDQ5OTkxOTU3OTM5MTQ5NTc3NTI1Njg1Mzc1NzA3MTYzNDMzMTgwMTQ0NTk3NzUwMDU3NzYyNzU2OTAxMTEyNTg1NDg3ODI0MjU5MjkxMDE0MzcyOTEyMzY5ODIxNjc4OTA1NTE5ODkzMTkyODczMTExMzIxMTIzODExMjc1Mjk0MzU2MDM0NDE1NDQ0OTE0NzM0NjEwMzU4ODE0NzM4ODE0NjAxNTU0NzMyODA3NDY1ODI4OTc2MTQ5Mjc5MzA3MjAwODk3OTE4NzE4MjA1NTM5NjM5NDc0NTAxMTk2MDA2MDE1MzM4NDIzODM1MjQyMDE2NDkwODg0ODgzMzI0NDQ0NjExNTc0ODg0OTA4NzU5MzM4MzQ4MTk5OTU0NDU5NTQ2NTgyNDM2OTYzNzY0MTY2NjMyMzU5NzMxNjUzNjIyNTEzMDM5OTk5MzU5NDU3NTc5NzM2NjUwNyIsICJlIjogIjEzMjQ3OTk4NjQ4Nzc1NzQwNDI0NjcwNzY0NjY4ODg0Nzk0ODkxMzg3OTI0NzY4MzAwNjg2MDcxMzU1OTU2NDc2OTY0NzMxNDkzMDYwODA1NjU4OTUxNTM0MDM0OTA4NTc0MTEzMzE4NDc2NzExNzY2NjA5OTAwNDc1MTQ0OTQ5NDg1OTEzMjkwOSIsICJ2IjogIjY2ODAwMTUxMzYyNzM5NTEyNTY2MzI5NDUwOTg4Njk1MTc4MDY3NjQzNDM4NzkxNTY4NjMwNzY3MzMxMjg4NjY5MDMzOTY3MTU3ODc4NDY0NjcwNDc5OTc4NTc3MDA5MDI1ODI1MzY1MjI4NjM1MTk4ODk5MzQ2NTM1NjM5OTIxMzkzNDg3NjM2MTIxNDQ5Mjk1NzQzNDgwNjk0NzAzODU5MTQ3MTg3MzMzNzY3MzY1MDQ5MTk4Nzg2Mzk1MTcyNjc3Nzk1MjcyNTc0NDQ4OTI1Nzg1MTgyNjQxNjA5MjI3MTgzOTYzNzQ1NDEyMDY1NzMwMzg5MTE1OTE0OTc3NjQ4NDUyMjI4Mjk2MTU1NTM0MjI5MDQ2OTIyMDE0MzQ3MTI2NDYwMDE4NjE4MDU1NDE2MTc4NDY5MzA0MDg3MTAwNTY1NDAxNjQ4NDU4Nzg5NjcyNjcyMzUwNjE0NjA3Mzc0Nzg5NTc0MTQ1NTQ0MDc5MTQzMjc4OTA3NjYwNjg0MzA4NjE0MjczODQxNzExOTk4MDA2NzU0MTc1ODQyNzM0OTMzMjIzMTczNjYyNDExMDU5Mjc3NzY3MzUwMDEzOTc1OTQ3Njc3MzAzMzUwOTI3MDQ5NTY4MjQwODcxNjYzMTgyMDg5MTM0MDk1MDYwNjE0NjY3Nzc2MjYyMDA2OTk0MDYzNjY4OTM1OTU0Mjc4NjYyNDM2OTM3MTMwNTM0NDcwNTE4MTM0NTAxMjk0NTIzOTk3MzE4OTM3MzE3NzU1NDYxMjA0OTQ1NTAwMTkyMTU3NjA1NDM1ODc3MzE4MTk4MTE1MDExOTczNDQxMjU2MjQ5NzcxNTYxNDQ4ODUxNzEzNDYyMTY2NTA0MDc0NjA1OTU4OTM5NDkzNDY5OTA3MDM2MTAzMjU4NTIwNDg3NTIwNjE5NjI5MTkzNjg4NDUzODg4MTA3OTQxNTY5MDY5NDA0MjA2NzA5Njc4NTk1MDIyNDgzMzc2NTc4NjQwNzk1ODI1OTM0OTkzMzIwNTQ2ODQxNjEzMzM1NDg3NjMxNzg3MDY0ODQyMzUyMjE4MDMxNDQ1MDMyNjk5OTM5NjAxODAyNDAyMjYyNTAxMDEyNzQyMDE4ODgyMDE2MDYyOTEwNDg1NDAxOTY4MzYwODkyMzA1Mzg2MjY2OTM3OTk4MTg0MzM2MDkwODk3NDk0MDA2ODc3NzA3MDE4MTIxNTc0MzgwMDUwMjQ1MiIsICJtIjogeyJpc3N1ZV9kYXRlIjogIjE0MDA1ODAxMDczMTU2NTU1MjExNjY3MTMzODYyMzg0MzkyODMyODE3NTk4MDQyMDM3OTU2NDc3NzI0MjM4NDg0NTQ4ODA4MzgzNzU2NzI3OTMyMTUxNzQwNzQ2OTEyODE3NDE4MzAyMDI2MzkzMzI5MjUxMjY0MzE3ODYxMDA0MTUyOTY0NjA3OTI3ODU0MzU5MTkyNzg1Mzg2MzUyNjQ0NzI1NTU2Nzk1MDQ3ODk5ODI2IiwgImRlc2NyaXB0aW9uIjogIjEwOTEyODE1NzEwNjcyMTc1MjYwNjY0NTE1NDEzNzExNDI3MTgyNDk2Njg3NjQ4MzE2MTg5ODE3OTk3NjExNjg3NDU4MjcxMDg1NDgwMDc2ODQ2NzQ0NTYxOTE3OTkxODE5MTQzNjIxMzQ5MjUyNDI5ODA2NTAwOTc1MDAzOTAyMzMyODUxNTU0MjA4ODg2MTkzOTk3NzUzOTc3MTMwNjIzNjc5NDI4MDIzMzU0NDk2MzA5IiwgIm1hc3Rlcl9zZWNyZXQiOiAiMTI3MzE1ODg4NTgyMjkxODc1NzAwNDY1NTczNTQ2NjY4NTg3MzM1MTU1OTgzMjc2NTM0MTA0NTExNzIzODcyODIyMDI5NDgzNzc5ODg0MzM4NTM2NDYyMjQ0ODAxODYzMDYyMTM5OTgwMjYxMjgzNjYwNjQxMTA1MTU3NzMxMTU2NDE1NzE1MTI5MTY3MDM3Mjk2MDIzMzA3OTQ5NjYyNTgxNDg4MjUwNTk0NzE2MDcyMTMifSwgIm0yIjogIjIxMDMzOTI2ODM5ODY5OTEwMDUyNjI1MzM1MjI1NTkyNjY0OTczNzEwMDQ2NzE2MTc5NzM5ODkzMzA3MzY0NDgwMDQzMDExNDQyNjQxNTY4Mzc3MzYyNzM1MjE5OTgwMDQxNDkyNTUyNDEyNTUyNjk0ODkzNDUyNDUyODkzOTQxMDk3MjU2NDcyNTgwMjkyMjYwOTkxNTI0ODcxNjM0Njk0MDI1MTY4NTU5NDM2ODE2MTcxNjkwNDI2NzA0NDcwOTc5NzY2NzkzMDAxOTgzMDc5OTA2MjkwMzQ4ODM0NzE4MTQxMTIxOTgyMTMwODUxNDU3OTUwOTU2MTM1MzM0NzkwNzQyOTYyMzc3MTM4OTQ1NjQ3NTAwMDc1MTAxNjQ5Njg2NTkwMjQxMjk1NDM3ODQwNjQ2MTM3Njc1OTE1NDc5OTcyODkxNjA4NDA4MDM3MDIzMDkyMjI4Nzk0NTk2NTQxNjk2MTIwNzIzODAyOTAwODM5Mjk4ODc2Mzc5MjMzNjAwMTMyNzYyNzcwOTcwNTE3Nzg5NDA0MzYyNjE5NDI1NTYxNjUwOTk3Mzc2NDQxMDQxODU5MDA2ODY3NjAyODYzMjA5OTA1NjM5MTMwMDY0NTI4OTM4MDU4Nzc1NjcwMzE4NjIzNTI2MjAxMzY2MTg5OTMyOTI2Mjg1MzEwNjk2NTc2NDczNDEwNzUxNTYyNTk0MjA5MzYxMjY0NDM2NjU3MzczMDQwMDQ2Nzg2NjA3MjEyNTM3OTM2NTE2MzYzODQyMDM4OTEzNTYwMjU4MDE0MjM1NTY3ODcwNDc1MzU3NzUwNTk2NTYwNDAwNDMyMzAwNzg3MTU3NTU0OTYyNDk4NDM0Mzg5ODM2OTA1OTUxOTc5ODU4Njc0OTQ0MzI1ODk4MDQxNTk3NDM2MTg4MzY4NTQ2MTQ3NTgxMzI3NDkwNjI4MjE3ODU3Njc3NDA0ODk2NzQ1NzcwNTMzMiJ9LCAiZ2VfcHJvb2ZzIjogW119LCAibm9uX3Jldm9jX3Byb29mIjogbnVsbH1dLCAiYWdncmVnYXRlZF9wcm9vZiI6IHsiY19oYXNoIjogIjM4MDMxNDM5OTM1ODAwMTYxODQyODg0NzQxNDgyNDY0MzI4MTEyMzgyOTUzMTE5MDY1MDIxNzc3MTM1MzQ1MjYxMjc3NjA4NjcwODk5IiwgImNfbGlzdCI6IFtbMSwgMjA4LCA0NywgMTYwLCA4MCwgNCwgOTMsIDQ2LCAxNjIsIDE4LCAxNjksIDM4LCAyMywgMTY0LCAxNjcsIDYyLCAzOCwgMTMxLCAyMzIsIDE3LCAxOTQsIDEwOCwgMTc5LCA4LCAyMDUsIDgsIDEzMCwgMjE4LCA3NSwgODIsIDQ3LCAyMDEsIDk1LCAyMDksIDQ3LCA1MCwgMTE3LCAyMjksIDI3LCAxOSwgOTQsIDE4NCwgNzgsIDEwNSwgMzUsIDIzNywgMTU1LCA3OSwgMTQ1LCAxMjYsIDIxOSwgMTM1LCAxOTEsIDU0LCAxNTgsIDIxMywgMjE3LCAxNzcsIDIzNywgMTIyLCAyMTUsIDM5LCA0MiwgMjA2LCA2MiwgNDIsIDE0MCwgNzYsIDEzNCwgMzUsIDE0NywgMTc2LCAzLCAyMjYsIDI1MiwgMTAwLCA1OSwgMzMsIDE0MSwgMjQ0LCAyNTEsIDE5NywgMTQzLCAxOTMsIDI0NSwgMTU2LCAyNTQsIDcwLCA0MywgNzYsIDQ5LCA0OSwgMjEzLCAyNCwgMTIyLCAxNTIsIDEwMCwgNzksIDIxMCwgMjIyLCAyMzYsIDE2MSwgMTc2LCAyMzUsIDU3LCAxNCwgMTY5LCA5MCwgMzAsIDIxLCAxNzMsIDQ0LCAxOTEsIDcyLCAyMzEsIDYwLCAzOSwgNywgMTQyLCAxNCwgMTA1LCAxODYsIDkzLCAyMDQsIDE1NCwgNDUsIDEyMiwgMjM1LCAxMTEsIDgsIDIwNSwgMTkzLCA4OSwgNzMsIDMzLCAyMDUsIDE0NiwgMTAwLCAxODEsIDI1NSwgOTgsIDYyLCAxOCwgMywgMjI3LCAxNTcsIDcyLCAyNDIsIDE0MSwgMTQyLCAyLCAyOCwgMzIsIDE0LCAxNTEsIDQyLCAzLCAxNTksIDE3MywgMjQwLCAyNTEsIDEzOCwgMTc4LCAxNDYsIDg3LCAyMzksIDIyOSwgMTA5LCAxOCwgMjA4LCAyMDEsIDQxLCA5NSwgMjYsIDEyOCwgNSwgNDMsIDI0NywgMjEwLCA5MiwgMTY0LCAzMywgMjksIDQzLCAxNTUsIDg1LCAxNDEsIDgxLCAxNzMsIDk4LCAxMDgsIDE0MiwgOTQsIDI0OCwgMTIxLCA3MCwgMTc3LCAxMDMsIDksIDAsIDIxOCwgMTAsIDE4MiwgMTczLCAyMiwgMTkyLCAxODgsIDYwLCAyNSwgMzcsIDYwLCA4MywgODYsIDIxNiwgNTMsIDE4OCwgMjMsIDEzLCAxMDIsIDQxLCAxOTksIDExNSwgMjA5LCAyMjgsIDksIDIzLCAxOTgsIDEwMiwgMTI2LCAzMiwgODcsIDEzMiwgMjA5LCAyMzIsIDg3LCAxMzAsIDYxLCAyMTcsIDEyMCwgMTQxLCA0MCwgMjIwLCAzMCwgMTU1LCAxLCAxNjQsIDIwMCwgMTE2LCAyMzUsIDIxMSwgMTIwLCA3NSwgMTM0LCAyMDgsIDY4LCA0NiwgMjM1XV19fSwgInJlcXVlc3RlZF9wcm9vZiI6IHsicmV2ZWFsZWRfYXR0cnMiOiB7fSwgInJldmVhbGVkX2F0dHJfZ3JvdXBzIjogeyJhdHRyIjogeyJzdWJfcHJvb2ZfaW5kZXgiOiAwLCAidmFsdWVzIjogeyJwZXJtaXNzaW9uX2xpc3QiOiB7InJhdyI6ICJbXCJPbmVTbWFydE1ldGVyTWljcm9zc2VydmljZVwiXSIsICJlbmNvZGVkIjogIjQyNjYzNzI0OTcxMzYwNjk2NjUyMDYxNjYyMjA0MTI2Mzg2ODY0MzA3NzQxNDgxODA4NDM0MTcyMjY3NDA1MzI3NjEwMDczOTIwNzY0In0sICJmdWxsX25hbWUiOiB7InJhdyI6ICJEYW5pbG8gTWFpYSIsICJlbmNvZGVkIjogIjEwNjU1NjQ4MDMxODI5MDQ4NTkwOTE4Mjc5NzM5MjUxMDY3NTM4NjQxMTY5ODMxNjE4MTk4NDY4MjcwMjMyMjY0Njc4OTY5MDE4OTY4MyJ9fX19LCAic2VsZl9hdHRlc3RlZF9hdHRycyI6IHt9LCAidW5yZXZlYWxlZF9hdHRycyI6IHt9LCAicHJlZGljYXRlcyI6IHt9fSwgImlkZW50aWZpZXJzIjogW3sic2NoZW1hX2lkIjogIkNHdjlkOEhFMkZrZWsyZjFweTJqN2c6MjplbnRpdHlfbWFuYWdlcnNfY3JlZGVudGlhbDoxLjAiLCAiY3JlZF9kZWZfaWQiOiAiQ0d2OWQ4SEUyRmtlazJmMXB5Mmo3ZzozOkNMOjEwOmRlZmF1bHQiLCAicmV2X3JlZ19pZCI6IG51bGwsICJ0aW1lc3RhbXAiOiBudWxsfV19\"\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"by_format\": {\n" +
                "    \"pres_request\": {\n" +
                "      \"indy\": {\n" +
                "        \"name\": \"Application Proof Request\",\n" +
                "        \"version\": \"1.0\",\n" +
                "        \"requested_attributes\": {\n" +
                "          \"attr\": {\n" +
                "            \"names\": [\n" +
                "              \"permission_list\",\n" +
                "              \"full_name\"\n" +
                "            ],\n" +
                "            \"restrictions\": [\n" +
                "              {\n" +
                "                \"issuer_did\": \"CGv9d8HE2Fkek2f1py2j7g\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        },\n" +
                "        \"requested_predicates\": {},\n" +
                "        \"nonce\": \"1121571858058687228991998\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"pres\": {\n" +
                "      \"indy\": {\n" +
                "        \"proof\": {\n" +
                "          \"proofs\": [\n" +
                "            {\n" +
                "              \"primary_proof\": {\n" +
                "                \"eq_proof\": {\n" +
                "                  \"revealed_attrs\": {\n" +
                "                    \"full_name\": \"106556480318290485909182797392510675386411698316181984682702322646789690189683\",\n" +
                "                    \"permission_list\": \"42663724971360696652061662204126386864307741481808434172267405327610073920764\"\n" +
                "                  },\n" +
                "                  \"a_prime\": \"58598058869119709676306864806346768122267084346557521874294854705092953563829785447516757961688644469384558935095583928672696918375544029089857431257665016541918467990362164840684314615345548573540528148362512484366586941171944488865323324853051119167282583442120320752760499919579391495775256853757071634331801445977500577627569011125854878242592910143729123698216789055198931928731113211238112752943560344154449147346103588147388146015547328074658289761492793072008979187182055396394745011960060153384238352420164908848833244446115748849087593383481999544595465824369637641666323597316536225130399993594575797366507\",\n" +
                "                  \"e\": \"132479986487757404246707646688847948913879247683006860713559564769647314930608056589515340349085741133184767117666099004751449494859132909\",\n" +
                "                  \"v\": \"668001513627395125663294509886951780676434387915686307673312886690339671578784646704799785770090258253652286351988993465356399213934876361214492957434806947038591471873337673650491987863951726777952725744489257851826416092271839637454120657303891159149776484522282961555342290469220143471264600186180554161784693040871005654016484587896726723506146073747895741455440791432789076606843086142738417119980067541758427349332231736624110592777673500139759476773033509270495682408716631820891340950606146677762620069940636689359542786624369371305344705181345012945239973189373177554612049455001921576054358773181981150119734412562497715614488517134621665040746059589394934699070361032585204875206196291936884538881079415690694042067096785950224833765786407958259349933205468416133354876317870648423522180314450326999396018024022625010127420188820160629104854019683608923053862669379981843360908974940068777070181215743800502452\",\n" +
                "                  \"m\": {\n" +
                "                    \"issue_date\": \"14005801073156555211667133862384392832817598042037956477724238484548808383756727932151740746912817418302026393329251264317861004152964607927854359192785386352644725556795047899826\",\n" +
                "                    \"description\": \"10912815710672175260664515413711427182496687648316189817997611687458271085480076846744561917991819143621349252429806500975003902332851554208886193997753977130623679428023354496309\",\n" +
                "                    \"master_secret\": \"12731588858229187570046557354666858733515598327653410451172387282202948377988433853646224480186306213998026128366064110515773115641571512916703729602330794966258148825059471607213\"\n" +
                "                  },\n" +
                "                  \"m2\": \"210339268398699100526253352255926649737100467161797398933073644800430114426415683773627352199800414925524125526948934524528939410972564725802922609915248716346940251685594368161716904267044709797667930019830799062903488347181411219821308514579509561353347907429623771389456475000751016496865902412954378406461376759154799728916084080370230922287945965416961207238029008392988763792336001327627709705177894043626194255616509973764410418590068676028632099056391300645289380587756703186235262013661899329262853106965764734107515625942093612644366573730400467866072125379365163638420389135602580142355678704753577505965604004323007871575549624984343898369059519798586749443258980415974361883685461475813274906282178576774048967457705332\"\n" +
                "                },\n" +
                "                \"ge_proofs\": []\n" +
                "              },\n" +
                "              \"non_revoc_proof\": null\n" +
                "            }\n" +
                "          ],\n" +
                "          \"aggregated_proof\": {\n" +
                "            \"c_hash\": \"38031439935800161842884741482464328112382953119065021777135345261277608670899\",\n" +
                "            \"c_list\": [\n" +
                "              [\n" +
                "                1,\n" +
                "                208,\n" +
                "                47,\n" +
                "                160,\n" +
                "                80,\n" +
                "                4,\n" +
                "                93,\n" +
                "                46,\n" +
                "                162,\n" +
                "                18,\n" +
                "                169,\n" +
                "                38,\n" +
                "                23,\n" +
                "                164,\n" +
                "                167,\n" +
                "                62,\n" +
                "                38,\n" +
                "                131,\n" +
                "                232,\n" +
                "                17,\n" +
                "                194,\n" +
                "                108,\n" +
                "                179,\n" +
                "                8,\n" +
                "                205,\n" +
                "                8,\n" +
                "                130,\n" +
                "                218,\n" +
                "                75,\n" +
                "                82,\n" +
                "                47,\n" +
                "                201,\n" +
                "                95,\n" +
                "                209,\n" +
                "                47,\n" +
                "                50,\n" +
                "                117,\n" +
                "                229,\n" +
                "                27,\n" +
                "                19,\n" +
                "                94,\n" +
                "                184,\n" +
                "                78,\n" +
                "                105,\n" +
                "                35,\n" +
                "                237,\n" +
                "                155,\n" +
                "                79,\n" +
                "                145,\n" +
                "                126,\n" +
                "                219,\n" +
                "                135,\n" +
                "                191,\n" +
                "                54,\n" +
                "                158,\n" +
                "                213,\n" +
                "                217,\n" +
                "                177,\n" +
                "                237,\n" +
                "                122,\n" +
                "                215,\n" +
                "                39,\n" +
                "                42,\n" +
                "                206,\n" +
                "                62,\n" +
                "                42,\n" +
                "                140,\n" +
                "                76,\n" +
                "                134,\n" +
                "                35,\n" +
                "                147,\n" +
                "                176,\n" +
                "                3,\n" +
                "                226,\n" +
                "                252,\n" +
                "                100,\n" +
                "                59,\n" +
                "                33,\n" +
                "                141,\n" +
                "                244,\n" +
                "                251,\n" +
                "                197,\n" +
                "                143,\n" +
                "                193,\n" +
                "                245,\n" +
                "                156,\n" +
                "                254,\n" +
                "                70,\n" +
                "                43,\n" +
                "                76,\n" +
                "                49,\n" +
                "                49,\n" +
                "                213,\n" +
                "                24,\n" +
                "                122,\n" +
                "                152,\n" +
                "                100,\n" +
                "                79,\n" +
                "                210,\n" +
                "                222,\n" +
                "                236,\n" +
                "                161,\n" +
                "                176,\n" +
                "                235,\n" +
                "                57,\n" +
                "                14,\n" +
                "                169,\n" +
                "                90,\n" +
                "                30,\n" +
                "                21,\n" +
                "                173,\n" +
                "                44,\n" +
                "                191,\n" +
                "                72,\n" +
                "                231,\n" +
                "                60,\n" +
                "                39,\n" +
                "                7,\n" +
                "                142,\n" +
                "                14,\n" +
                "                105,\n" +
                "                186,\n" +
                "                93,\n" +
                "                204,\n" +
                "                154,\n" +
                "                45,\n" +
                "                122,\n" +
                "                235,\n" +
                "                111,\n" +
                "                8,\n" +
                "                205,\n" +
                "                193,\n" +
                "                89,\n" +
                "                73,\n" +
                "                33,\n" +
                "                205,\n" +
                "                146,\n" +
                "                100,\n" +
                "                181,\n" +
                "                255,\n" +
                "                98,\n" +
                "                62,\n" +
                "                18,\n" +
                "                3,\n" +
                "                227,\n" +
                "                157,\n" +
                "                72,\n" +
                "                242,\n" +
                "                141,\n" +
                "                142,\n" +
                "                2,\n" +
                "                28,\n" +
                "                32,\n" +
                "                14,\n" +
                "                151,\n" +
                "                42,\n" +
                "                3,\n" +
                "                159,\n" +
                "                173,\n" +
                "                240,\n" +
                "                251,\n" +
                "                138,\n" +
                "                178,\n" +
                "                146,\n" +
                "                87,\n" +
                "                239,\n" +
                "                229,\n" +
                "                109,\n" +
                "                18,\n" +
                "                208,\n" +
                "                201,\n" +
                "                41,\n" +
                "                95,\n" +
                "                26,\n" +
                "                128,\n" +
                "                5,\n" +
                "                43,\n" +
                "                247,\n" +
                "                210,\n" +
                "                92,\n" +
                "                164,\n" +
                "                33,\n" +
                "                29,\n" +
                "                43,\n" +
                "                155,\n" +
                "                85,\n" +
                "                141,\n" +
                "                81,\n" +
                "                173,\n" +
                "                98,\n" +
                "                108,\n" +
                "                142,\n" +
                "                94,\n" +
                "                248,\n" +
                "                121,\n" +
                "                70,\n" +
                "                177,\n" +
                "                103,\n" +
                "                9,\n" +
                "                0,\n" +
                "                218,\n" +
                "                10,\n" +
                "                182,\n" +
                "                173,\n" +
                "                22,\n" +
                "                192,\n" +
                "                188,\n" +
                "                60,\n" +
                "                25,\n" +
                "                37,\n" +
                "                60,\n" +
                "                83,\n" +
                "                86,\n" +
                "                216,\n" +
                "                53,\n" +
                "                188,\n" +
                "                23,\n" +
                "                13,\n" +
                "                102,\n" +
                "                41,\n" +
                "                199,\n" +
                "                115,\n" +
                "                209,\n" +
                "                228,\n" +
                "                9,\n" +
                "                23,\n" +
                "                198,\n" +
                "                102,\n" +
                "                126,\n" +
                "                32,\n" +
                "                87,\n" +
                "                132,\n" +
                "                209,\n" +
                "                232,\n" +
                "                87,\n" +
                "                130,\n" +
                "                61,\n" +
                "                217,\n" +
                "                120,\n" +
                "                141,\n" +
                "                40,\n" +
                "                220,\n" +
                "                30,\n" +
                "                155,\n" +
                "                1,\n" +
                "                164,\n" +
                "                200,\n" +
                "                116,\n" +
                "                235,\n" +
                "                211,\n" +
                "                120,\n" +
                "                75,\n" +
                "                134,\n" +
                "                208,\n" +
                "                68,\n" +
                "                46,\n" +
                "                235\n" +
                "              ]\n" +
                "            ]\n" +
                "          }\n" +
                "        },\n" +
                "        \"requested_proof\": {\n" +
                "          \"revealed_attrs\": {},\n" +
                "          \"revealed_attr_groups\": {\n" +
                "            \"attr\": {\n" +
                "              \"sub_proof_index\": 0,\n" +
                "              \"values\": {\n" +
                "                \"permission_list\": {\n" +
                "                  \"raw\": \"[\\\"OneSmartMeterMicrosservice\\\"]\",\n" +
                "                  \"encoded\": \"42663724971360696652061662204126386864307741481808434172267405327610073920764\"\n" +
                "                },\n" +
                "                \"full_name\": {\n" +
                "                  \"raw\": \"Danilo Maia\",\n" +
                "                  \"encoded\": \"106556480318290485909182797392510675386411698316181984682702322646789690189683\"\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"self_attested_attrs\": {},\n" +
                "          \"unrevealed_attrs\": {},\n" +
                "          \"predicates\": {}\n" +
                "        },\n" +
                "        \"identifiers\": [\n" +
                "          {\n" +
                "            \"schema_id\": \"CGv9d8HE2Fkek2f1py2j7g:2:entity_managers_credential:1.0\",\n" +
                "            \"cred_def_id\": \"CGv9d8HE2Fkek2f1py2j7g:3:CL:10:default\",\n" +
                "            \"rev_reg_id\": null,\n" +
                "            \"timestamp\": null\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"verified\": \"true\",\n" +
                "  \"verified_msgs\": [\n" +
                "    \"RMV_GLB_NRI\"\n" +
                "  ],\n" +
                "  \"auto_present\": false,\n" +
                "  \"auto_remove\": false\n" +
                "}\n";

        holderService.json(payload);
    }
}
