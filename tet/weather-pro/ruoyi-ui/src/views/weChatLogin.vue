<template>
</template>

<script>

export default {
  name: "WeChatLogin",
  data() {
    return {
      
    };
  },
  async created() {
    
    //获取路由参数
    let path = this.$route.query.path;
    let code = this.$route.query.code;

    if(this.$store.getters.token){
      window.alert("sometext");
      this.$router.push(path).catch(()=>{});
      return;
    }
    
    //登录认证
    this.$store.dispatch("WeChatLogin", code).then(() => {
      this.$router.push(path).catch(()=>{});
    })
  }
};
</script>


