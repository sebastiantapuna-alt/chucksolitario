@echo off
curl.exe -s -X POST -H "Content-Type: application/json" -d "{\"contents\":[{\"parts\":[{\"text\":\"cuanto es 34 + 2 \"}]}]}" "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=AQ.FakeKeyBlahBlahBlah"
