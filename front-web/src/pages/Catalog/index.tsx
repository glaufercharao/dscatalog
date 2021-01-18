import React, { useEffect, useState } from 'react';
import { makeRequest } from 'core/utils/request';
import { Link } from 'react-router-dom';
import ProductCard from './components/ProductCard';
import './style.scss';
import { ProductsResponse } from 'core/types/Product';
import ProductCardLoader from './components/Loaders/ProductCardLoader';
import Pagination from 'core/components/Pagination';
const Catalog =()=>{

   const [productsResponse, setProductsResponse] = useState<ProductsResponse>()
   const [isLoading, setIsLoading] = useState(false)
   const [activePage, setActivepage] = useState(0); 

   useEffect(()=>{
    const params ={
        page:activePage,
        linesPerPage:12
    }
    setIsLoading(true);   
    makeRequest({url:'/product', params})
        .then(response => setProductsResponse(response.data))
        .finally(() => { setIsLoading(false); })
   },[activePage]);

    return (
        <div className="catalog-container">
            <h1 className="catalog-title">
                Catálogo de produtos
            </h1>
            <div className="catalog-products">
                {isLoading ? <ProductCardLoader /> :(
                    productsResponse?.content.map(product =>(
                        <Link to={`/products/${product.id}`} key={product.id}>
                            <ProductCard product={product}/>
                        </Link>   
                    ))
                )}
            </div>
            {productsResponse && (
                <Pagination 
                    totalPages={productsResponse?.totalPages} 
                    activePage={activePage}
                    onChange={page => setActivepage(page)}
                />
             )}
        </div>
    );
}

export default Catalog;