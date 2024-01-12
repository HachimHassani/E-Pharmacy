#build front end
cd e-pharmacy-frontend
npm install
npm run build
cd ..

#ENABLE WHEN BACKEND IS READY

#build backend
#cd e-pharmacy-backend
#chmod +x mvnw
#./mvnw spring-boot:build-image
#cd ..