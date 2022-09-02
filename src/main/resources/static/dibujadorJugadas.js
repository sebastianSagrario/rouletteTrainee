
                //retorna un elemento imagen qeu representa un pilon lo uso para seleccionar clases usando el array jugada 
                const basicoClase=["pl","m","c","ca","l"];
                            
                function moverFichasMedio(ficha,nMedio,pos,fichAcum)
                {
                    switch(nMedio)
                    {
                            case 1:
                                ficha.style.right=`${pos}px`;
                                if (fichAcum>=5)
                                {
                                    ficha.style.bottom="0px"
                                }
                                break;
                            case 2:
                                    ficha.style.top=`${pos}px`;
                                    if (fichAcum>=5)
                                    {
                                        ficha.style.right="10px"
                                    }
                                    break;
                            case 3:
                                    ficha.style.top=`${pos}px`;
                                    if (fichAcum>=5)
                                    {
                                        ficha.style.left="10px"
                                    }
                                    break;
                            case 4:
                                    ficha.style.right=`${pos}px`;
                                    if (fichAcum>=5)
                                    {
                                        ficha.style.top="10px"
                                    }
                                    break;
                        }   
                }

                //en el paño ubica un tipo basico una determinada cantidad de fichas
                function armar(basico,cantidad)
                {
                    let hermanos;
                    hermanos=determinarDim(basico);
                    let arregloRepartido=repartir(cantidad,hermanos);
                    for (let i=0;i<arregloRepartido.length;i++)
                    {
                        ubicar(basico,arregloRepartido[i],i+1);
                    }
                }

                function ubicar(basico,cantidad,nBasico)
                {

                    console.log(`.s${basicoClase[basico]}${nBasico}`);
                    let sector=document.querySelector(`.s${basicoClase[basico]}${nBasico}`);
                    let sectorFrag=document.createDocumentFragment();    
                    if (cantidad>=10)
                    {
                        //se supone que solo va a haber plenos que lleguen a esta instancia
                        let pilon=generarPilon(`${basicoClase[basico]}`);
                        ubicarPilon(basico,nBasico,pilon);
                        sectorFrag.appendChild(pilon);
                        cantidad-=10;
                    }
                    let pos=setPosicionInicial(basico,cantidad);
                    for(let i=1;i<=cantidad;i++)
                    {
                        let ficha=generarFicha(`${basicoClase[basico]}${nBasico}`);
                        moverFicha(ficha,basico,nBasico,pos,i);
                        sectorFrag.appendChild(ficha);
                        pos+=13;
                    }
                    sector.appendChild(sectorFrag);
                }

                //acomoda las margenes de la ficha para que este en la pos que le corresponde
                function moverFicha(ficha,basico,nBasico,pos,fichasAcum)
                {
                    switch(basico)
                    {
                        case 0:
                            ficha.style.top=`${pos}px`;
                            if (fichasAcum>5)
                            {
                                ficha.style.right="40px";
                            }
                            break;
                        case 1:
                            moverFichasMedio(ficha,nBasico,pos,fichasAcum)
                            break;
                        case 2:
                            moverFichasCuadro(ficha,nBasico,pos,fichasAcum);
                            break;
                        case 3:
                            ficha.style.bottom=`${pos}px`;
                            if (fichasAcum>5)
                            {
                                ficha.style.right="40px";
                            }
                            break;
                        case 4:
                            ficha.style.bottom=`${pos}px`;
                            if (fichasAcum>5)
                            {
                                ficha.style.right="40px";
                            }
                            break;
                    }
                }


                function generarPilon(clase)
                {
                    console.log(clase);
                    let pilon=document.createElement("img");
                    pilon.src="/pilon.png";
                    pilon.className+=`ficha ${clase}`;
                    return pilon;
                }


                function ubicarPilon(basico,nBasico,pilon)
                {
                    if (basico==0)
                    {    
                        pilon.style.top="35%";            
                        pilon.style.left="70%";
                    }
                }
                //mueve el elemento "ficha" una magnitud "Pos" de acuerdo a su tipo de "basico" y a su "numeroDeBasico" (cuadro 3 o medio 4 por ejemplo) hace un corte de acuerdo a la cantidad de fichasAcum  
                function determinarDim(basico)
                {
                    if (basico==0 || basico==3)
                    {
                        return 1;
                    }
                    if (basico==1 || basico==2)
                    {
                        return 4;
                    }
                    if (basico==4)
                    {
                        return 2;
                    }
                }


                //basico numero que dice que tipo de basico (pleno 0,medio 1,cuadro 2, calle 3 o linea 4)
                // cantiadad cantidad de basicos 
                function setPosicionInicial(basico,cantidad)
                {
                    let posI;
                    if (basico<2)
                    {
                        posI=50-(cantidad/2)*12;
                    }
                    else if (basico==2)
                    {
                        posI=-25;
                    }
                    else
                    {
                        posI=130;
                    }
                    return posI;
                }

                //devuelve una ficha con la clase de basico correspondiente
                function generarFicha(claseUbicar)
                {
                    let ficha=document.createElement("img");
                    ficha.src="/ficha.png";
                    ficha.className+="ficha "
                    ficha.className+=claseUbicar;
                    return ficha;
                }

                //cambia la ubiacion de una ficha en un cuadro
                //elementoIMG la ficha que se va a ubicar
                //numero nBasico es el numero del basico 
                // numero pos la posicion en la que se va a ubicar la ficha
                // numero fichaCorte valor para saber si es necesario hacer un corte
                function moverFichasCuadro(ficha,nBasico,pos,fichaCorte)
                {    
                    let corrimiento=0;
                    if(fichaCorte>5){                
                        corrimiento+=15;
                    }
                    switch(nBasico)
                    {
                        case 1:            
                                ficha.style.right=`${pos}px`;            
                                ficha.style.bottom=`${pos+corrimiento}px`;
                            break;
                        case 2:
                                ficha.style.left=`${pos}px`;            
                                ficha.style.bottom=`${pos+corrimiento}px`;            
                            break;
                        case 3:
                            ficha.style.right=`${pos}px`;            
                            ficha.style.top=`${pos+corrimiento}px`;            
                            break;
                        case 4:
                            ficha.style.left=`${pos}px`;
                            ficha.style.top=`${pos+corrimiento}px`;                            
                            break;
                    }
                }

                //crea un arreglo que reparte de forma aleatoria  el parametro cantidad, cantidad: la cnatidad de fichas, dimension : la dimension del arreglo
                function repartir(cantidad,dimension)
                {
                    let agrega;
                    let arrMedios=new Array(dimension);
                    let restantes=cantidad;
                    let i=0;
                    
                    while( restantes>0 && i<dimension)
                    {     
                        agrega=Math.floor( Math.random()*(restantes-1)) +1;
                        arrMedios[i]=agrega;
                        restantes-=agrega;
                        i++;
                    }
                    if (i>=dimension && restantes!=0)
                    {
                        arrMedios[--i]+=restantes;
                    }
                    console.log(arrMedios);
                    return arrMedios;
                }
