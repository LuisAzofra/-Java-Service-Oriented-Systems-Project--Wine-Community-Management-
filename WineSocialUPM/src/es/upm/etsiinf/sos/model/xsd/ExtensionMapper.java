
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package es.upm.etsiinf.sos.model.xsd;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://model.sos.etsiinf.upm.es/xsd".equals(namespaceURI) &&
                  "Username".equals(typeName)){
                   
                            return  es.upm.etsiinf.sos.model.xsd.Username.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.sos.etsiinf.upm.es/xsd".equals(namespaceURI) &&
                  "User".equals(typeName)){
                   
                            return  es.upm.etsiinf.sos.model.xsd.User.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.sos.etsiinf.upm.es/xsd".equals(namespaceURI) &&
                  "PasswordPair".equals(typeName)){
                   
                            return  es.upm.etsiinf.sos.model.xsd.PasswordPair.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.sos.etsiinf.upm.es/xsd".equals(namespaceURI) &&
                  "Wine".equals(typeName)){
                   
                            return  es.upm.etsiinf.sos.model.xsd.Wine.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.sos.etsiinf.upm.es/xsd".equals(namespaceURI) &&
                  "Response".equals(typeName)){
                   
                            return  es.upm.etsiinf.sos.model.xsd.Response.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.sos.etsiinf.upm.es/xsd".equals(namespaceURI) &&
                  "WinesRatedList".equals(typeName)){
                   
                            return  es.upm.etsiinf.sos.model.xsd.WinesRatedList.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.sos.etsiinf.upm.es/xsd".equals(namespaceURI) &&
                  "WineRated".equals(typeName)){
                   
                            return  es.upm.etsiinf.sos.model.xsd.WineRated.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.sos.etsiinf.upm.es/xsd".equals(namespaceURI) &&
                  "FollowerList".equals(typeName)){
                   
                            return  es.upm.etsiinf.sos.model.xsd.FollowerList.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.sos.etsiinf.upm.es/xsd".equals(namespaceURI) &&
                  "WineList".equals(typeName)){
                   
                            return  es.upm.etsiinf.sos.model.xsd.WineList.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.sos.etsiinf.upm.es/xsd".equals(namespaceURI) &&
                  "AddUserResponse".equals(typeName)){
                   
                            return  es.upm.etsiinf.sos.model.xsd.AddUserResponse.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    