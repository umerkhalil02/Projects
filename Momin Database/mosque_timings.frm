TYPE=VIEW
query=select `m`.`Name` AS `Name`,`t`.`Fajr` AS `Fajr`,`t`.`Zuhr` AS `Zuhr`,`t`.`Asr` AS `Asr`,`t`.`Maghrib` AS `Maghrib`,`t`.`Isha` AS `Isha`,`t`.`Jumma` AS `Jumma` from `dbms`.`mosque` `m` join `dbms`.`timings` `t` where `m`.`mosqueId` = `t`.`mosqueId`
md5=6063b11675c0cac12e33e1fe63c52b4b
updatable=1
algorithm=0
definer_user=
definer_host=
suid=2
with_check_option=0
timestamp=2022-07-21 20:47:36
create-version=2
source=SELECT m.Name, t.Fajr , t.Zuhr, t.Asr, t.Maghrib, t.Isha, t.Jumma\nFROM mosque as m, timings as t\nWHERE m.mosqueId = t.mosqueId
client_cs_name=utf8mb4
connection_cl_name=utf8mb4_unicode_ci
view_body_utf8=select `m`.`Name` AS `Name`,`t`.`Fajr` AS `Fajr`,`t`.`Zuhr` AS `Zuhr`,`t`.`Asr` AS `Asr`,`t`.`Maghrib` AS `Maghrib`,`t`.`Isha` AS `Isha`,`t`.`Jumma` AS `Jumma` from `dbms`.`mosque` `m` join `dbms`.`timings` `t` where `m`.`mosqueId` = `t`.`mosqueId`
mariadb-version=100424
