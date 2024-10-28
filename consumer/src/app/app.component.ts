import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, take} from 'rxjs';

@Component({
  selector: 'app-root',
  // standalone: true,
  // imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'consumer';

  private catalogueApi = 'http://catalogue:8080/catalogue/all';
  private httpHeaders: HttpHeaders = new HttpHeaders({
    'Content-Type':'application/json; charset=utf-8'
  });

  constructor(private http: HttpClient) { }



  onClickMe() {
    console.log('clicked')
    let products: any[] = [];
    let cart: any = undefined;

    this.http.get<any[]>('apic/catalogue/all?tags=geek&tags=green&pageNum=0&pageSize=10&order=description')
      .pipe(take(1))
      .subscribe({
        next: (data: any) => {
          console.log(data);
          products = data;
        },
        error: (er: any) => {
          console.log(er)
        }
      });

    this.http.get<any[]>('http://localhost:8089/catalogue/all?tags=geek&tags=green&pageNum=0&pageSize=10&order=description')
      .pipe(take(1))
      .subscribe({
        next: (data: any) => {
          console.log(data);
          products = data;
          // create cart
          this.http.post<any>('http://localhost:8088/carts/create', {"customerId":"111111111111111111111122"}, {headers: this.httpHeaders})
            .pipe(take(1))
            .subscribe({
              next: (data: any) => {
                console.log(data);
                cart = data;

                products.forEach(prod => {
                    this.http.post<any>(
                      'http://localhost:8088/carts/add/item',
                      {
                        "cartId": cart["id"],
                        "quantity": 1,
                        "unitPrice": prod["price"],
                        "productId": prod["id"]
                      }
                    )
                      .pipe(take(1))
                      .subscribe({
                        next: (data: any) => {
                          console.log(data);
                      }})
                })

            }})


        },
        error: (er: any) => {
          console.log(er);
        }
      });

    console.log(products);
    console.log(cart);

  }
}
