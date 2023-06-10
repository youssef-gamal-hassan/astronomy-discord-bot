import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class main extends ListenerAdapter  {
    public static void updateCommands(JDA jda){
        CommandListUpdateAction commands = jda.updateCommands();
        commands.addCommands(Commands.slash("moon-phase", "Today's Moon Phase").addOptions(new OptionData(OptionType.STRING, "moon-style", "pick your preferred moon style", true).addChoice("default", "default").addChoice("sketch", "sketch").addChoice("shaded", "shaded")));
        commands.queue();
        System.out.println("Commands Updated!");
    }
    public String moonHttpRequest(String moonStyle){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new JSONObject(new MoonPhase(new MoonStyle(moonStyle, "stars", "black", "white", "white"), new MoonObserver(30.064286239773992f, 31.493958476697806f, "2023-06-10"))).toString());
        Request request = new Request.Builder().url("https://api.astronomyapi.com/api/v2/studio/moon-phase").addHeader("Authorization", "Bearer ZmQ5Y2M2ZjQtMGQ3ZS00MGJiLTg2OGQtOTZjNjg3OWEyMGZmOjE1ZjViYjc0N2I4YjY3ZmU3Mjg2MTJlNWEwZGVkZTdlNjNiYjhiYjk1ZDA3MDhhZDlmNzA3YWVkYTdjNzU5OWRjMjdhMjdkM2Q5YTVhOTVhMDFjNmUxZTgxZTdmYTIzNzdhOWI3ZDk1YzQ4MmYyODEyYmVkMmM3NmU2MDhjZGQyOWI3YmJiODY3Y2M5NzhkY2U0OTE5Njc3YWZjZjA0Zjk1Y2MzNTNiN2MyMGFkY2E3NDczODMzNDBlYzAyZTkzODRkNTcwYjRmY2FhNjhkYmU5NGYzYWM4ZDI1YzA3YWUx").post(body).build();
        Call call = client.newCall(request);
        try (Response response = call.execute()) {
            JSONObject obj = new JSONObject(response.body().string());
            String image = obj.getJSONObject("data").getString("imageUrl");
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {



        JDA jda = JDABuilder.createDefault(System.getenv("token"))
                .addEventListeners(new main())
                .enableIntents(GatewayIntent.GUILD_VOICE_STATES)
                .setActivity(Activity.watching("The Stars"))
                .build();


        jda.awaitReady();
    }


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "say":
                int count = 1;
                StringBuilder content = new StringBuilder();
                if (event.getOption("times-to-repeat") != null) {
                    count = event.getOption("times-to-repeat").getAsInt();
                }
                for (int i = 0; i < count; i++) {
                    content.append(event.getOption("shit-to-repeat").getAsString()).append("\n");
                }
                event.reply(content.toString()).queue();
                break;
            case "jmt":
                event.reply("JMT el kalb").queue();
                break;
            case "lara":
                if(event.getOption("is-stupid").getAsBoolean()){
                    event.reply("Dumbass lara fuck you").queue();
                }
                else {
                    event.reply("fuck you lara").queue();
                }
                break;
            case "morad":
                if(event.getOption("is-bird").getAsBoolean()){
                    event.reply("I'm a bird").queue();
                }
                else{
                    event.reply("ya deeny ya farida").queue();
                }

                break;
            case "moon-phase":
                if (event.getOption("moon-style") != null){
                    event.reply(moonHttpRequest(event.getOption("moon-style").getAsString())).queue();
                }
                else {
                    event.reply(moonHttpRequest("default")).queue();
                }
                break;
                default:
                event.reply("What the fuck?!").queue();
        }
    }

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        if (event.getChannelJoined() == null){
            return;
        }
        switch(event.getMember().getId()){
            case "462753867449237506":
                event.getGuild().getTextChannelById("1105589927451820072").sendMessage("JMT El Kalb Da5al el VC").queue();
                break;
            case "321367134699520000":
                event.getGuild().getTextChannelById("1105589927451820072").sendMessage("ABO MOZE 7ADAR").queue();
                break;
            case "932010472268505159":
                event.getGuild().getTextChannelById("1105589927451820072").sendMessage("EWWWW LARA").queue();
                break;
            default:
                return;

        }
    }

}
