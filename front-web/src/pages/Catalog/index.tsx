import React, { useEffect, useState } from 'react';
import { makeRequest } from '../../core/utils/request';
import { Link } from 'react-router-dom';
import ProductCard from './components/ProductCard';
import './style.scss';
import { ProductsResponse } from '../../core/types/Product';
const Catalog =()=>{

   const [productsResponse, setProductsResponse] = useState<ProductsResponse>() 

   useEffect(()=>{
    const params ={
        page:0,
        linesPerPage:12
    }   
    makeRequest({url:'/product', params})
        .then(response => setProductsResponse(response.data));
   },[]);

    return (
        <div className="catalog-container">
            <h1 className="catalog-title">
                Catálogo de produtos
            </h1>
            <div className="catalog-products">
                {productsResponse?.content.map(product =>(
                    <Link to={`/products/${product.id}`} key={product.id}>
                        <ProductCard product={product}/>
                    </Link>   
                ))}
            </div>
        </div>
    );
}

export default Catalog;