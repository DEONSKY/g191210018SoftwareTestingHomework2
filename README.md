Uygulamayı ayağa kaldırır ve remote modu kullanır: 
docker compose stop && docker-compose run app

Eclipse ile uygulama ayağa kaldırılıcaksa programın başlangıç argümanı olarak
local yada remote verilebilir. local bilgisayarda yüklü chrome'a bağlanırken
sürümden dolayı uyumsuzluk içerebilir. remote parametresi ise standlone-chrome
image'ine bağlanmak için kullanılır. Ve default remote dur. Ama docker image'ı dışında
uygulamayı ayağa kaldırırken local yada remote parametresi verilmesi zorunludur

ÖNEMLİ!! lib klasörü boşsa içine aşağıdaki linkteki klasörleri yükleyin.
https://drive.google.com/drive/folders/1TsGgTO_16xoy1yYYEVU4Na7qoDexoRoQ?usp=sharing

Not: Eğer uygulamada beklendik bir hata ile karşılaşırsanız yeniden ayağa kaldırın

docker compose stop && docker-compose run app