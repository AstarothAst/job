При старте запускается два джоба: com.example.job.job.CustomJobOne и com.example.job.job.CustomJobTwo
Изначально джобы исполняются раз в 3 секунды.

Запросы

GET localhost:8080/api/faster

GET localhost:8080/api/slower

управляют частотой исполнения com.example.job.job.CustomJobTwo

Минимальная частота выполнения 10 сек, максимальная 1 сек
